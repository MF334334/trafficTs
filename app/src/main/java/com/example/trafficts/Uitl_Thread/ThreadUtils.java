package com.example.trafficts.Uitl_Thread;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtils {
    //主线程的Handler
    public final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static void postMainThread(Runnable runnbale) {
        MAIN.post(runnbale);
    }


}


