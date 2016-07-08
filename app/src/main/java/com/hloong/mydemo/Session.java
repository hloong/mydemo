package com.hloong.mydemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


/**
 * The Client Seesion Object for wgc client, contains some necessary
 * information.
 *  1，对请求header参数的保存
 *  2，对用户信息的保存
 *  3，对一些时间之类的保存
 *  4，一些小的数据的保存以作判断用
 */
public class Session extends Observable {
    private Context context;


    // 客户端版本码Int
    private int versionCode;
    private String vCode;
    // 客户端版本码String
    private String versionName;
    // 系统版本
    private String release;
    // SDK版本
    private String sdkInt;
    // 串号
    private String imei;
    private static Session instance = new Session();
    private SharedPreferences mSharedPreferences;//保存本地资料
    private Map<String, String> headers = new HashMap<String, String>();// 请求头
    public Map<String, String> getHeaders() {
        headers.put("client-sysName", "Android");
        headers.put("client-sysVersion", Build.VERSION.RELEASE);
//    	headers.put("Content-Type", "application/json; charset=utf-8");
//		if (!TextUtils.isEmpty(getToken())) {
//		    headers.put("access_token", getToken());
//	    }
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Session() {
    }
    public static Session getInstance(){
        return instance;
    }

    /**
     * 获取应用信息
     */
    private void getApplicationInfo() {
        final PackageManager pm = (PackageManager) context.getPackageManager();
        try {
            final PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            vCode = "" + pi.versionCode;
            versionCode = pi.versionCode;
            TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imei = telMgr.getDeviceId();
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            // Log.d(TAG, "met some error when get application info");
        }
    }


    /**
     * 得到包名
     */
    public static String getPackageName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            return pInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 获取app版本号
     *
     * @return
     */
    public String getSdkInt() {
        return sdkInt;
    }

    /**
     * 获取手机串号
     *
     * @return
     */
    public String getImei() {
        return imei;
    }


    public void clearSharedPreferences() {
        mSharedPreferences.edit().clear().commit();
    }
}