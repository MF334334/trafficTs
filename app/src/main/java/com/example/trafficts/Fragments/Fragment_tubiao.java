package com.example.trafficts.Fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.trafficts.Bean.ChuXingFangShiBean;
import com.example.trafficts.Bean.ChuxingMuDiBean;
import com.example.trafficts.Bean.YongduBean;
import com.example.trafficts.Presenter.ChuXingFangShilmpl;
import com.example.trafficts.Presenter.ChuXingMuDilmpl;
import com.example.trafficts.Presenter.GetYongDulmpl;
import com.example.trafficts.Presenter.IGetChuXingFangShiJsonPresenter;
import com.example.trafficts.Presenter.IGetChuXingMuDiJsonPresenter;
import com.example.trafficts.Presenter.IGetYongduJsonPresenter;
import com.example.trafficts.R;
import com.example.trafficts.Time_Util.GetDate;
import com.example.trafficts.Toasthelper.ToastHelper;
import com.example.trafficts.Uitl_Thread.ThreadUtils;
import com.example.trafficts.View.IChuXingFangShiView;
import com.example.trafficts.View.IChuXingMuDiView;
import com.example.trafficts.View.IYongduView;
import com.example.trafficts.custom.MyMarkerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Fill;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM;

public class Fragment_tubiao extends Fragment implements IYongduView, IChuXingMuDiView, IChuXingFangShiView {
    private Unbinder unbinder;
    @BindView(R.id.chart2)
    LineChart chart2;
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.chart3)
    BarChart chart3;
    @BindView(R.id.chart4)
    PieChart chart4;
    private Typeface tfLight, tfRegular;

    private View view;

    private IGetYongduJsonPresenter iGetYongduJsonPresenter;
    private IGetChuXingMuDiJsonPresenter iGetChuXingMuDiJsonPresenter;
    private IGetChuXingFangShiJsonPresenter iGetChuXingFangShiJsonPresenter;


    private long[] historyTime = GetDate.getHistroyTimeOfSixHours();
    private int[] value_chart1 = {0, 0, 0, 0, 0, 0};
    private int count_chart1 = 0;
    ArrayList<Entry> mValues = new ArrayList<>();

    float[] travelPurpose = {0, 0, 0, 0, 0};
    float[] travelModel = {0, 0, 0, 0};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        view = inflater.inflate(R.layout.fragment_tubiao, container, false);
        unbinder = ButterKnife.bind(this, view);
        mValues.add(new Entry(0, 10, "08-1"));
        mValues.add(new Entry(1, 15, ""));
        mValues.add(new Entry(2, 25, ""));
        mValues.add(new Entry(3, 19, ""));
        mValues.add(new Entry(4, 25, "08-10"));
        mValues.add(new Entry(5, 16, ""));
        mValues.add(new Entry(6, 40, ""));
        mValues.add(new Entry(7, 24, ""));
        mValues.add(new Entry(8, 27, "08-21"));

        presentersImpl();
        presentersQuery();
        setChart1();
//        setData1(6, 100);  //设置表1 数据 在6个数据全部取到的getYongduJsonVIew中
        setChart2();
        setData2(6, 100);
        setChart3();
//        setData3(5, 100);  //设置表3 数据 在4个数据全部取到的getChuxingJFangShisonVIew中
        setChart4();
//        setData4(5, 100); //设置表4 数据 在5个数据全部取到的getChuxingJsonVIew中

        return view;
    }

    private void presentersImpl() {
        iGetYongduJsonPresenter = new GetYongDulmpl(this);
        iGetChuXingMuDiJsonPresenter = new ChuXingMuDilmpl(this);
        iGetChuXingFangShiJsonPresenter = new ChuXingFangShilmpl(this);
    }

    private void presentersQuery() {
        ThreadUtils.postMainThread(runnable_yongDuZhiShu);
        ThreadUtils.postMainThread(runnable_ChuXingMuDi);
        ThreadUtils.postMainThread(runnable_ChuXingFangShi);


        // TODO: 2020/4/21  
//        iGetYongduJsonPresenter.getYongDuJson(3, "123.4344,41.8111", "1538566733378");
//        iGetChuXingMuDiJsonPresenter.getChuXing(3, "123.4344,41.8111", "1538566733378");
        ToastHelper.showToast(getContext(), String.valueOf(System.currentTimeMillis()));
    }

    private Runnable runnable_yongDuZhiShu = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < historyTime.length; i++) {
                iGetYongduJsonPresenter.getYongDuJson(3, "123.4344,41.8111", String.valueOf(historyTime[i]));
                //从久时间 到 近时间
            }
        }

    };

    private Runnable runnable_ChuXingMuDi = new Runnable() {
        @Override
        public void run() {
            iGetChuXingMuDiJsonPresenter.getChuXing(3, "123.4344,41.8111", "1538566733378");
        }

    };
    private Runnable runnable_ChuXingFangShi = new Runnable() {
        @Override
        public void run() {
            iGetChuXingFangShiJsonPresenter.getChuXingFangShi(3, "123.4344,41.8111", "1538566733378");
        }

    };

    private void setChart1() {
        // no description text
        chart1.getDescription().setEnabled(false);

        // enable touch gestures
        chart1.setTouchEnabled(false);

        chart1.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        chart1.setDragEnabled(false);
        chart1.setScaleEnabled(false);
        chart1.setDrawGridBackground(false);
        chart1.setHighlightPerDragEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        chart1.setPinchZoom(false);

        // set an alternative background color
        chart1.setBackgroundColor(Color.WHITE);
        chart1.animateX(1500);

        Legend l = chart1.getLegend();//图例
        l.setEnabled(false);   //是否使用 图例

        XAxis xAxis = chart1.getXAxis();
        xAxis.setTypeface(tfRegular);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.RED);
        xAxis.setPosition(BOTTOM);
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值


        YAxis leftAxis = chart1.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(R.color.text_black);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(true);

        YAxis axisRight = chart1.getAxisRight();
        axisRight.setDrawLabels(false);
        axisRight.setDrawGridLines(false);


    }

    private void setData1(int count, int range) {

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * range);  随机数据
            values.add(new Entry(i, value_chart1[i], getResources().getDrawable(R.drawable.star)));
        }
        LineDataSet set1;

        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);

            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "拥堵指数");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(R.color.pink_high);
            set1.setCircleColor(R.color.text_delete_gray);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(true);
            set1.setDrawIcons(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            chart1.setData(data);
            chart1.invalidate(); //刷新chahrt1


        }

    }

    private void setChart2() {

        // background color
        chart2.setBackgroundColor(Color.WHITE);

        // disable description text
        chart2.getDescription().setEnabled(true);

        // enable touch gestures
        chart2.setTouchEnabled(false);


        chart2.setDrawGridBackground(false);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(chart2);
        chart2.setMarker(mv);

        // enable scaling and dragging
        chart2.setDragEnabled(true);
        chart2.setScaleEnabled(true);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        chart2.setPinchZoom(true);

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart2.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setPosition(BOTTOM);

        }
        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart2.getAxisLeft();


            // disable dual axis (only use LEFT axis)
            chart2.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(100f);
            yAxis.setAxisMinimum(0f);
        }
        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(70f, "预警值");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            ll1.setTypeface(tfRegular);

//            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
//            ll2.setLineWidth(4f);
//            ll2.enableDashedLine(10f, 10f, 0f);
//            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//            ll2.setTextSize(10f);
//            ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
//            yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);
            // draw points over time
            chart2.animateX(1500);
            // get the legend (only possible after setting data)
//            Legend l = chart2.getLegend();

            // draw legend entries as lines
//            l.setForm(Legend.LegendForm.LINE);
            chart2.getLegend().setEnabled(false);
            chart2.setDescription(null);
        }
    }

    private void setData2(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range);
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (chart2.getData() != null &&
                chart2.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart2.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart2.getData().notifyDataChanged();
            chart2.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(true);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart2.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.color.text_belowblue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart2.setData(data);
        }

    }

    private void setChart3() {
        chart3.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart3.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart3.setPinchZoom(false);
        chart3.setDrawBarShadow(false);
        chart3.setDrawGridBackground(false);
        chart3.getAxisLeft().setDrawGridLines(false);
        chart3.getAxisLeft().setAxisMaximum(100f); //设置最大100
        XAxis xAxis = chart3.getXAxis();
        xAxis.setPosition(BOTTOM);
        xAxis.setDrawGridLines(true);





        // add a nice and smooth animation
        chart3.animateY(1500);

        chart3.getLegend().setEnabled(false);
        chart3.setTouchEnabled(false);//禁止点击
    }

    private void setData3(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count ; i++) {
                values.add(new BarEntry(i, travelModel[i-1]));
        }

        BarDataSet set1;

        if (chart3.getData() != null &&
                chart3.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart3.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart3.getData().notifyDataChanged();
            chart3.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "出行方式");

            set1.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);

            List<Fill> gradientFills = new ArrayList<>();
            gradientFills.add(new Fill(startColor1, startColor1));
            gradientFills.add(new Fill(startColor2, startColor2));
            gradientFills.add(new Fill(startColor3, startColor3));
            gradientFills.add(new Fill(startColor4, startColor4));
            gradientFills.add(new Fill(startColor5, startColor5));

            set1.setFills(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(tfLight);
            data.setBarWidth(0.5f);

            chart3.setData(data);
            chart3.invalidate(); //刷新chahrt1

        }
    }

    private void setChart4() {
        chart4.setUsePercentValues(true);
        chart4.getDescription().setEnabled(false);
        chart4.setExtraOffsets(5, 10, 5, 5);

        chart4.setDragDecelerationFrictionCoef(0.95f);

        chart4.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        chart4.setDrawHoleEnabled(true);
        chart4.setHoleColor(Color.WHITE);

        chart4.setTransparentCircleColor(Color.WHITE);
        chart4.setTransparentCircleAlpha(110);

        chart4.setHoleRadius(58f);
        chart4.setTransparentCircleRadius(61f);

        chart4.setDrawCenterText(true);

        chart4.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart4.setRotationEnabled(true);
        chart4.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener

        chart4.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart4.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);


    }

    private void setData4(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry(travelPurpose[i], parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);//点击区块后的放大情况

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80.f);
        dataSet.setValueLinePart1Length(0.4f);
        dataSet.setValueLinePart2Length(0.4f);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        chart4.setData(data);

        // undo all highlights
        chart4.highlightValues(null);

        chart4.invalidate();
    }

    protected final String[] parties = new String[]{
            "教育", "旅游", "消费", "医疗", "工作"
    };

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void getYongduJson(YongduBean yongduBean) {

        String value = yongduBean.getValue();
        double d = Double.valueOf(value);
        String time = yongduBean.getTimestamp();
//        ToastHelper.showToast(getContext(), String.valueOf(value));
        value_chart1[count_chart1] = (int) d;

        count_chart1++;
        if (count_chart1 >= 6) {
            count_chart1 = 0;
            setData1(6, 200);

        }


    }

    @Override
    public void getChuxingFangShiJson(ChuXingFangShiBean chuXingFangShiBean) {
        float bus = chuXingFangShiBean.getBus();
        float privatecar = chuXingFangShiBean.getPrivatecar();
        float subway = chuXingFangShiBean.getSubway();
        float walk = chuXingFangShiBean.getWalk();
        travelModel[0] = bus;
        travelModel[1] = privatecar;
        travelModel[2] = subway;
        travelModel[3] = walk;

        setData3(4, 100);
    }

    @Override
    public void getChuxingJson(ChuxingMuDiBean chuxingMuDiBean) {
        float edu = chuxingMuDiBean.getEducationArea();
        float travel = chuxingMuDiBean.getTravelArea();
        float industry = chuxingMuDiBean.getIndustryArea();
        float medical = chuxingMuDiBean.getMedicalArea();
        float business = chuxingMuDiBean.getBusinessArea();
        travelPurpose[0] = edu;
        travelPurpose[1] = travel;
        travelPurpose[2] = industry;
        travelPurpose[3] = medical;
        travelPurpose[4] = business;
        setData4(5, 100);


    }


}
