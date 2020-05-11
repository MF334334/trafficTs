package com.example.trafficts.Presenter;


import com.example.trafficts.Bean.ChuxingMuDiBean;
import com.example.trafficts.Http.HttpUtils;
import com.example.trafficts.Http.Urls;
import com.example.trafficts.Http.helper.RetrofitHelper;
import com.example.trafficts.Http.service.IGetChuXingMuDiServer;
import com.example.trafficts.View.IChuXingMuDiView;

import rx.Observable;


public class ChuXingMuDilmpl implements IGetChuXingMuDiJsonPresenter {
   private IChuXingMuDiView iChuXingMuDiView;

    public ChuXingMuDilmpl(IChuXingMuDiView iChuXingMuDiView) {
        this.iChuXingMuDiView = iChuXingMuDiView;
    }
    @Override
    public void getChuXing(int disRange, String lngAndLat, String timestamp) {
        Observable<ChuxingMuDiBean> observable = RetrofitHelper.getService(Urls.HOST, IGetChuXingMuDiServer.class).getChucingGson(disRange,lngAndLat,timestamp);
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<ChuxingMuDiBean>() {
            @Override
            public void onSuccess(ChuxingMuDiBean chuxingMuDiBean) {
                iChuXingMuDiView.getChuxingJson(chuxingMuDiBean);
            }

            @Override
            public void onError(Throwable error, String msg) {

            }
        });
    }


}

