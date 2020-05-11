package com.example.trafficts.Http.service;


import com.example.trafficts.Bean.IDJson;

import retrofit2.http.GET;
import rx.Observable;

public interface IGetIDGsonServer {
    @GET("JSON")
    Observable<IDJson> getIDGson();

}
