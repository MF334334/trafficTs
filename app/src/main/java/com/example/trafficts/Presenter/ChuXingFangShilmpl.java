package com.example.trafficts.Presenter;


import com.example.trafficts.Bean.ChuXingFangShiBean;
import com.example.trafficts.Bean.ChuxingMuDiBean;
import com.example.trafficts.Http.HttpUtils;
import com.example.trafficts.Http.Urls;
import com.example.trafficts.Http.helper.RetrofitHelper;
import com.example.trafficts.Http.service.IGetChuXingFangShiServer;
import com.example.trafficts.View.IChuXingFangShiView;

import rx.Observable;


public class ChuXingFangShilmpl implements IGetChuXingFangShiJsonPresenter {
   private IChuXingFangShiView iChuXingFangShiView;

    public ChuXingFangShilmpl(IChuXingFangShiView iChuXingFangShiView) {
        this.iChuXingFangShiView = iChuXingFangShiView;
    }


    @Override
    public void getChuXingFangShi(int disRange, String lngAndLat, String timestamp) {
            Observable<ChuXingFangShiBean> observable = RetrofitHelper.getService(Urls.HOST, IGetChuXingFangShiServer.class).getChuXingFangShi(disRange,lngAndLat,timestamp);
            HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<ChuXingFangShiBean>() {
                @Override
                public void onSuccess(ChuXingFangShiBean chuXingFangShiBean) {
                    iChuXingFangShiView.getChuxingFangShiJson(chuXingFangShiBean);
                }

                @Override
                public void onError(Throwable error, String msg) {

                }
            });
        }
    }


