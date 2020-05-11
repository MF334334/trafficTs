package com.example.trafficts.Http.service;


import com.example.trafficts.Bean.ChuXingFangShiBean;
import com.example.trafficts.Bean.ChuxingMuDiBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IGetChuXingFangShiServer {
    @GET("android/travel_model/")
    Observable<ChuXingFangShiBean> getChuXingFangShi(@Query("disRange") int disRange, @Query("lngAndLat") String lngAndLat, @Query("timestamp") String timestamp);

}
