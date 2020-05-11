package com.example.trafficts.Http.helper;



import com.example.trafficts.MyApplication;

import java.io.File;

import okhttp3.Cache;


/**
 * Created by zlc on 2016/10/19.
 * 缓存帮助类
 */

public class CacheHelper {

    private Cache mCache;
    //设置缓存目录
    private static File cacheFile;
    private static long maxSize = 8*1024*1024;

    private CacheHelper(){

        cacheFile = new File(
                MyApplication.getContext()
                .getCacheDir()
                .getAbsolutePath()
                , "wjlxCache");
        if(!cacheFile.exists()){
            cacheFile.mkdir();
        }
    }


    private static CacheHelper helper;

    public static CacheHelper getInstance(){
        if(helper==null){
            synchronized (CacheHelper.class){
                if(helper==null){
                    helper = new CacheHelper();
                }
            }
        }
        return helper;
    }


    //返回缓存对象
    public Cache getCache(){
        if(mCache ==null) {
            mCache = new Cache(cacheFile, maxSize);
        }
        return mCache;

    }
}
