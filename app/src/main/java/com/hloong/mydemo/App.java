package com.hloong.mydemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.hloong.mydemo.util.ACrash;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/6/27.
 */
public class App extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        ACrash.getInstance().setCustomCrashInfo(this);
        SDKInitializer.initialize(this);//百度定位SDK
        LeakCanary.install(this);//检查内存泄漏工具启动
    }
}
