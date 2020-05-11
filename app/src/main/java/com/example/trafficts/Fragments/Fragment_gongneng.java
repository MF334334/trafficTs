package com.example.trafficts.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amap.api.maps.utils.overlay.SmoothMoveMarker;
import com.example.trafficts.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_gongneng extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.relative_map)
    RelativeLayout relativeMap;

    @BindView(R.id.btn_ceshi)
    Button btnCeShi;
    @BindView(R.id.linear_juecegongneng)
    LinearLayout linearJuecegongneng;

    @BindView(R.id.text_jjuecegongeng)
    TextView textJjuecegongeng;
    @BindView(R.id.linear_juecegongneng_tubiao)
    LinearLayout linearJuecegongnengTubiao;

    @BindView(R.id.text_jichugongneg)
    TextView textJichugongneg;
    @BindView(R.id.linear_jichugongneng_tubiao)
    LinearLayout linearJichugongnengTubiao;

    @BindView(R.id.img_zhiliuzhijian)
    ImageView imgZhiliuzhijian;


    private Animation pushLetftOut, pushLetftIn, pushRightIn, pushRightOut;
    private static boolean jiChuShow = false;
    private static boolean jueCeShow = false;

    /*
     *地图组件
     */
    @BindView(R.id.map)
    MapView mMapView;
    AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;


    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mMapView.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //通过参数中的布局填充获取对应布局
        view = inflater.inflate(R.layout.fragment_gongneng, container, false);
        unbinder = ButterKnife.bind(this, view);

        mMapView.onCreate(savedInstanceState);//初始化地图容器
        mapLoad();
        animationLoad(); //基础 决策功能按钮排动画
        jichuAndjueceClick();//基础 决策功能按钮点击事件

//        showHeatMap();
        showTrack();

        return view;
    }

    private void showHeatMap() {
        //生成热力点坐标列表
        LatLng[] latlngs = new LatLng[500];
        double x = 30.222916;
        double y = 120.184735;

        for (int i = 0; i < 500; i++) {
            double x_ = 0;
            double y_ = 0;
            x_ = Math.random() * 0.5 - 0.25;
            y_ = Math.random() * 0.5 - 0.25;
            latlngs[i] = new LatLng(x + x_, y + y_);
        }
        // 构建热力图 HeatmapTileProvider
        HeatmapTileProvider.Builder builder = new HeatmapTileProvider.Builder();
        builder.data(Arrays.asList(latlngs)) // 设置热力图绘制的数据
        ; // 设置热力图渐变，有默认值 DEFAULT_GRADIENT，可不设置该接口
// Gradient 的设置可见参考手册
// 构造热力图对象
        HeatmapTileProvider heatmapTileProvider = builder.build();

        // 初始化 TileOverlayOptions
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.tileProvider(heatmapTileProvider); // 设置瓦片图层的提供者
// 向地图上添加 TileOverlayOptions 类对象
        aMap.addTileOverlay(tileOverlayOptions);
    }

    private List<LatLng> readLatLngs() {
        List<LatLng> list = new ArrayList<LatLng>();
        list.add(new LatLng(120.128085, 30.332957));
        list.add(new LatLng(120.133716, 30.320797));
        list.add(new LatLng(120.147192, 30.304829));
        list.add(new LatLng(120.147192, 30.304829));
        list.add(new LatLng(120.174143, 30.208737));
        list.add(new LatLng(120.157985, 30.170047));


        return list;
    }

    private void showTrack() {
        List<LatLng> points = new ArrayList<>();
        points.add(new LatLng(120.128085, 30.332957));
        points.add(new LatLng(120.133716, 30.320797));
        points.add(new LatLng(120.147192, 30.304829));
        points.add(new LatLng(120.174143, 30.208737));
        points.add(new LatLng(120.157985, 30.170047));
        // 获取轨迹坐标点 120.128085,30.332957 120.133716,30.320797  120.147192,30.304829 120.147192,30.304829  120.174143,30.208737 120.157985,30.170047
//        List<LatLng> points = readLatLngs();
        LatLngBounds bounds = new LatLngBounds(points.get(0), points.get(points.size() - 2));
        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));

        SmoothMoveMarker smoothMarker = new SmoothMoveMarker(aMap);
// 设置滑动的图标
        smoothMarker.setDescriptor(BitmapDescriptorFactory.fromResource(R.drawable.buhang));

        LatLng drivePoint = points.get(0);
        Pair<Integer, LatLng> pair = SpatialRelationUtil.calShortestDistancePoint(points, drivePoint);
        points.set(pair.first, drivePoint);
        List<LatLng> subList = points.subList(pair.first, points.size());

// 设置滑动的轨迹左边点
        smoothMarker.setPoints(subList);
// 设置滑动的总时间
        smoothMarker.setTotalDuration(40);
// 开始滑动
        smoothMarker.startSmoothMove();
    }

    private void mapLoad() {


//初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        //定位样式
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


//初始化定位
        mLocationClient = new AMapLocationClient(getContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);

        permissionAccessToLocal();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。

        showTrack();

    }

    private void permissionAccessToLocal() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            mLocationClient.startLocation();
            Toast.makeText(getContext(), "已开启定位权限", Toast.LENGTH_LONG).show();
        }
    }


    //异步获取定位结果
    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //解析定位结果
                }
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    //启动定位
                    mLocationClient.startLocation();
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getActivity(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }


    private void animationLoad() {
        pushLetftOut = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_out);
        pushRightIn = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_in);
        pushLetftIn = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_in);
        pushRightOut = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_out);

    }

    private void jichuAndjueceClick() {
        textJichugongneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jichuShow();
                //显示基础各项按钮
            }
        });
        textJjuecegongeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jueceShow();
                //显示决策各项按钮
            }
        });
        mMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                if (jiChuShow) {
                    jichuHide();
                }
                if (jueCeShow) {
                    jueceHide();
                }
            }
        });


//        imgZhiliuzhijian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastHelper.showToast(getContext(), "ssssss");
//            }
//        });

    }

    private void jichuShow() {
        jiChuShow = true;
        textJichugongneg.setVisibility(View.GONE);
        textJichugongneg.startAnimation(pushLetftOut);
        linearJichugongnengTubiao.setVisibility(View.VISIBLE);
        linearJichugongnengTubiao.startAnimation(pushRightIn);

    }

    private void jichuHide() {
        jiChuShow = false;
        textJichugongneg.setVisibility(View.VISIBLE);
        textJichugongneg.startAnimation(pushLetftIn);
        linearJichugongnengTubiao.setVisibility(View.GONE);
        linearJichugongnengTubiao.startAnimation(pushRightOut);

    }

    private void jueceShow() {
        jueCeShow = true;
        textJjuecegongeng.setVisibility(View.GONE);
        textJjuecegongeng.startAnimation(pushLetftOut);
        linearJuecegongnengTubiao.setVisibility(View.VISIBLE);
        linearJuecegongnengTubiao.startAnimation(pushRightIn);

    }

    private void jueceHide() {
        jueCeShow = false;
        textJjuecegongeng.setVisibility(View.VISIBLE);
        textJjuecegongeng.startAnimation(pushLetftIn);
        linearJuecegongnengTubiao.setVisibility(View.GONE);
        linearJuecegongnengTubiao.startAnimation(pushRightOut);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
