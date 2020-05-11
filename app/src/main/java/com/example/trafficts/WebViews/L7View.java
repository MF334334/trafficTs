package com.example.trafficts.WebViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.trafficts.Bean.IDJson;
import com.example.trafficts.Toasthelper.ToastHelper;
import com.github.abel533.echarts.json.GsonOption;

public class L7View extends WebView {

    private static final String TAG = L7View.class.getSimpleName();

    public L7View(Context context) {
        this(context, null);

    }

    public L7View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public L7View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        loadUrl("file:///android_asset/L7.html");
    }

    /**
     * 刷新图表
     * java调用js的loadEcharts方法刷新echart
     * 不能在第一时间就用此方法来显示图表，因为第一时间html的标签还未加载完成，不能获取到标签值
     *
     * @param option
     */
    public void refreshEchartsWithOption(GsonOption option) {
        if (option == null) {
            return;
        }
        String optionString = option.toString();
        String call = "javascript:loadEcharts('" + optionString + "')";
        loadUrl(call);
    }

    public void getIDJSon(IDJson idJson){
        String IDGson = idJson.toString();
        String call = "javascript:loaddata('"+IDGson+"')";
        loadUrl(call);
       ToastHelper.showToast(getContext(),"数据库数据获取成功");
    }

    public void load(){
        String call = "javascript:load()";
        loadUrl(call);
    }

    public void qiehuan(){
        String call = "javascript:qiehuan()";
        loadUrl(call);
        ToastHelper.showToast(getContext(),"切换成功");
    }
   }

