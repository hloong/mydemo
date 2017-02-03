package com.hloong.mydemo;

import android.app.Application;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.hloong.mydemo.util.ACrash;
import com.squareup.leakcanary.LeakCanary;
import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.NewPatchListener;

/**
 * Created by Administrator on 2016/6/27.
 */
public class App extends Application{
    String appVersion;
    String appId = "71316-1";

    public static App instance = new App();
    public static App getInstance(){
        return instance;
    }
    public App(){

    }

    @Override
    public void onCreate() {
        super.onCreate();
        ACrash.getInstance().setCustomCrashInfo(this);
        SDKInitializer.initialize(this);//百度定位SDK
        LeakCanary.install(this);//检查内存泄漏工具启动

        try {
            appVersion = this.getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (Exception e) {
            this.appVersion = "1.0.0";
        }

        HotFixManager.getInstance().initialize(this, appVersion,appId, mNewPatchListener);//阿里百川HotFix初始化
    }

    /**
     * 如果有一个patch在运行，那么需要提示重启应用更新补丁
     */
    NewPatchListener mNewPatchListener = new NewPatchListener() {
        @Override
        public void handlePatch(int patchVersion) {
            // TODO do something
            Toast.makeText(App.this, "请重启应用更新补丁", Toast.LENGTH_SHORT).show();
        }
    };
}
