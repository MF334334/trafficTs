package com.example.trafficts.Http.service;


import com.example.trafficts.Bean.IDJson;
import com.example.trafficts.Bean.YongduBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface IGetYongduServer {
    @GET("android/Congestion_index/")
    Observable<YongduBean> getYongduGson(@Query("disRange") int disRange, @Query("lngAndLat") String lngAndLat, @Query("timestamp") String timestamp);

}
