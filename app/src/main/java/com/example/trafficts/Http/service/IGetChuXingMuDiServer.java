package com.example.trafficts.Http.service;


import com.example.trafficts.Bean.ChuxingMuDiBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IGetChuXingMuDiServer {
    @GET("android/travel_purpose/")
    Observable<ChuxingMuDiBean> getChucingGson(@Query("disRange") int disRange, @Query("lngAndLat") String lngAndLat, @Query("timestamp") String timestamp);

}
