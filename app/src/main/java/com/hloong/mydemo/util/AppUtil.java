package com.hloong.mydemo.util;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * Created by hl on 16/4/9.
 */
public class AppUtil {

    /**
     * 获得屏幕像素(宽度)
     * */
    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕像素(高度)
     * */
    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取手机串号(IMEI)
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (imei == null) {
            return "";
        } else {
            return imei;
        }
    }

    /**
     * 获取手机信息
     *
     * @param context
     * @return
     */
    public static String getSystemInfo(Activity context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BOARD" + android.os.Build.BOARD + "\n");
        stringBuffer.append("BRAND:" + android.os.Build.BRAND + "\n");
        stringBuffer.append("CPU_ABI" + android.os.Build.CPU_ABI + "\n");
        stringBuffer.append("DEVICE:" + android.os.Build.DEVICE + "\n");
        stringBuffer.append("DISPLAY:" + android.os.Build.DISPLAY + "\n");
        stringBuffer.append("FINGERPRINT:" + android.os.Build.FINGERPRINT + "\n");
        stringBuffer.append("HOST:" + android.os.Build.HOST + "\n");
        stringBuffer.append("ID:" + android.os.Build.ID + "\n");
        stringBuffer.append("MANUFACTURER:" + android.os.Build.MANUFACTURER + "\n");
        stringBuffer.append("MODEL:" + android.os.Build.MODEL + "\n");
        stringBuffer.append("PRODUCT:" + android.os.Build.PRODUCT + "\n");
        stringBuffer.append("TAGS:" + android.os.Build.TAGS + "\n");
        stringBuffer.append("TIME:" + android.os.Build.TIME + "\n");
        stringBuffer.append("TYPE:" + android.os.Build.TYPE + "\n");
        stringBuffer.append("USER:" + android.os.Build.USER + "\n");
        stringBuffer.append("VERSION.CODENAME:" + android.os.Build.VERSION.CODENAME + "\n");
        stringBuffer.append("VERSION.INCREMENTAL:" + android.os.Build.VERSION.INCREMENTAL + "\n");
        stringBuffer.append("VERSION.RELEASE:" + android.os.Build.VERSION.RELEASE + "\n");
        stringBuffer.append("VERSION.SDK:" + android.os.Build.VERSION.SDK + "\n");
        stringBuffer.append("VERSION.SDK_INT:" + android.os.Build.VERSION.SDK_INT + "\n");
        stringBuffer.append("VERSION_CODES.BASE:" + android.os.Build.VERSION_CODES.BASE + "\n");
        stringBuffer.append("VERSION_CODES.BASE_1_1:" + android.os.Build.VERSION_CODES.BASE_1_1 + "\n");
        stringBuffer.append("VERSION_CODES.CUPCAKE" + android.os.Build.VERSION_CODES.CUPCAKE + "\n");
        stringBuffer.append("VERSION_CODES.CUR_DEVELOPMENT:" + android.os.Build.VERSION_CODES.CUR_DEVELOPMENT + "\n");
        stringBuffer.append("VERSION_CODES.DONUT:" + android.os.Build.VERSION_CODES.DONUT + "\n");
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        stringBuffer.append("Density:" + dm.density + "\n");
        stringBuffer.append("DensityDpi:" + dm.densityDpi + "\n");
        stringBuffer.append("Width:" + dm.widthPixels + "\n");
        stringBuffer.append("Height:" + dm.heightPixels + "\n");
        return stringBuffer.toString();
    }
}
