package com.hloong.mydemo.util;

import android.content.Context;

public class DisplayUtils {

    /**
     * 获得屏幕宽度(像素)
     * */
    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕高度(像素)
     * */
    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获得屏幕密度
     * （0.75 / 1.0 / 1.5）
     * */
    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获得屏幕密度DPI
     * （120 / 160 / 240）
     * */
    public static int getDensity_DPI(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * px转成dp，保证尺寸大小不变
     * @param context
     * @param px
     * @return
     */
    public static int px2dp(Context context,float px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale + 0.5f);
    }

    /**
     * dp转成px，保证尺寸大小不变
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context,float dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5f);
    }

    /**
     * px转成sp，保证文字大小不变
     * @param context
     * @param px
     * @return
     */
    public static int px2sp(Context context,float px){
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(px/scale + 0.5f);
    }

    /**
     * sp转成px，保证文字大小不变
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context,float sp){
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp*scale + 0.5f);
    }

}
