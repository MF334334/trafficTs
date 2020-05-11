package com.example.trafficts.Presenter;


import com.example.trafficts.Bean.IDJson;
import com.example.trafficts.Bean.YongduBean;
import com.example.trafficts.Http.HttpUtils;
import com.example.trafficts.Http.Urls;
import com.example.trafficts.Http.helper.RetrofitHelper;

import com.example.trafficts.Http.service.IGetYongduServer;
import com.example.trafficts.View.IYongduView;

import rx.Observable;


public class GetYongDulmpl implements IGetYongduJsonPresenter {
    private IYongduView iYongduView;

    public GetYongDulmpl(IYongduView iYongduView) {
        this.iYongduView = iYongduView;
    }
    @Override
    public void getYongDuJson(int disRange, String lngAndLat, String timestamp) {
        Observable<YongduBean> observable = RetrofitHelper.getService(Urls.HOST, IGetYongduServer.class).getYongduGson(disRange,lngAndLat,timestamp);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<YongduBean>() {
            @Override
            public void onSuccess(YongduBean yongduBean) {
                iYongduView.getYongduJson(yongduBean);
            }

            @Override
            public void onError(Throwable error, String msg) {

            }
        });
    }
}

