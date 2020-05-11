package com.example.trafficts.Presenter;






import com.example.trafficts.Bean.IDJson;
import com.example.trafficts.Http.HttpUtils;
import com.example.trafficts.Http.Urls;
import com.example.trafficts.Http.helper.RetrofitHelper;

import com.example.trafficts.Http.service.IGetIDGsonServer;
import com.example.trafficts.View.IIDGsonView;

import rx.Observable;


public class GetIDJsonlmpl implements IGetIDJsonPresenter {
    private IIDGsonView iidGsonView;

    public GetIDJsonlmpl(IIDGsonView iidGsonView) {
        this.iidGsonView = iidGsonView;
    }

    @Override
    public void getIDJson() {
        Observable<IDJson> observable = RetrofitHelper.getService(Urls.HOST, IGetIDGsonServer.class).getIDGson();
        HttpUtils.requestNetByGet(observable, new HttpUtils.OnResultListener<IDJson>() {
            @Override
            public void onSuccess(IDJson idJson) {
                iidGsonView.getIDJson(idJson);
            }

            @Override
            public void onError(Throwable error, String msg) {

            }
        });
    }


}

