package com.example.trafficts.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.trafficts.Adapters_framents.FragmentsAdapters;
import com.example.trafficts.Fragments.Fragment_gongneng;
import com.example.trafficts.Fragments.Fragment_tubiao;
import com.example.trafficts.Fragments.Fragment_zhibiao;
import com.example.trafficts.R;
import com.example.trafficts.Util_PagerView.GalleryTransformer;
import com.example.trafficts.Util_PagerView.SuperViewPager;
import com.example.trafficts.Util_PagerView.ViewPageHelper;
import com.example.trafficts.Utils.PublicValues;
import com.example.trafficts.Utils.ToggleAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewDataACtivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.ViewPager_container)
    SuperViewPager ViewPagerContainer;
    @BindView(R.id.text_A)
    RadioButton textA;
    @BindView(R.id.text_B)
    RadioButton textB;
    @BindView(R.id.text_C)
    RadioButton textC;
    @BindView(R.id.text_D)
    RadioButton textD;
    @BindView(R.id.radio_text)
    LinearLayout radioText;
    @BindView(R.id.radio_gongneng)
    RadioButton radioGongneng;
    @BindView(R.id.radio_tubiao)
    RadioButton radioTubiao;
    @BindView(R.id.radio_zhibiao)
    RadioButton radioZhibiao;
    @BindView(R.id.radio_wode)
    RadioButton radioWode;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radio_A)
    TextView radioA;
    @BindView(R.id.radio_B)
    TextView radioB;
    @BindView(R.id.radio_C)
    TextView radioC;
    @BindView(R.id.radio_D)
    TextView radioD;
    @BindView(R.id.img_shaixuan)
    ImageView imgShaixuan;

    private List<Fragment> fragmentLists;
    FragmentsAdapters fragmentsAdapters;
    private ViewPageHelper viewPageHelper;
    ToggleAnimation toggleAnimation; //拖动效果


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data_activity);
        ButterKnife.bind(this);
        radioGroup.setOnCheckedChangeListener(this);
        radioCheck();

        viewPageHelper = new ViewPageHelper(ViewPagerContainer);
        //初始化viewpager
        initFragments();
        //加载fragmentList
        setFragmenAdapter();

        toggleAnimation = new ToggleAnimation(imgShaixuan, 800f);
        toggleAnimation.init();
        toggleAnimation.touch();
        imgShaixuan.bringToFront();
    }

    private void initFragments() {
        fragmentLists = new ArrayList<Fragment>();

        fragmentLists.add(new Fragment_gongneng());
        fragmentLists.add(new Fragment_tubiao());
        fragmentLists.add(new Fragment_zhibiao());
        fragmentLists.add(new Fragment_gongneng());


    }

    //RadioButton 切换
    private void setDefaultItem(int position) {
        viewPageHelper.setCurrentItem(position, true);
        fragmentsAdapters.notifyDataSetChanged();
    }

    private void setFragmenAdapter() {
        ViewPagerContainer.setOffscreenPageLimit(7); //pageview 最大允许7张同时出现  避免VIewpager 销毁fragment 重新生成需要走网络的浪费
        ViewPagerContainer.setPageTransformer(true, new GalleryTransformer()); //滑动效果
        ViewPagerContainer.setPageMargin(0);//相邻间隔
        fragmentsAdapters = new FragmentsAdapters(getSupportFragmentManager(), fragmentLists);
        //添加适配器
        ViewPagerContainer.setAdapter(fragmentsAdapters);
        //添加滑动监听器
        ViewPagerContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // TODO: 2020/4/11  切换对应radiobtn
                currentItemToRadioCheck(position);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void radioCheck() {
        radioGongneng.toggle();//默认选中功能
        radioA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGongneng.toggle();
                setDefaultItem(0);

            }
        });
        radioB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioTubiao.toggle();
                setDefaultItem(1);

            }

        });
        radioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioZhibiao.toggle();
                setDefaultItem(2);

            }
        });
        radioD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioWode.toggle();
                setDefaultItem(3);

            }
        });
    }

    private void currentItemToRadioCheck(int position) {
        PublicValues.CurrentItem = position;  //标志所在位置，若为地图，则viewpager中消化滑动
        switch (position) {
            case 0:
                radioGongneng.toggle();
                break;
            case 1:
                radioTubiao.toggle();
                break;
            case 2:
                radioZhibiao.toggle();
                break;
            case 3:
                radioWode.toggle();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


    }
}
