package com.example.trafficts;

import android.app.Application;
import android.content.Context;



public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        context=getApplicationContext();
//        SPHelper.init(context);

    }
}