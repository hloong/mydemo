package com.hloong.mydemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharedPerferences进行封装基本的方法
 * Created by hloong on 2016/6/24.
 */
public class SpHelper {
    private static final String PATH = "hloong_shared";
    private static SpHelper instance;
    private static SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static SpHelper getInstance(Context context){
        if (instance == null && context != null){
            instance = new SpHelper(context);
        }
        return instance;
    }

    private SpHelper(Context context){
        sp = context.getSharedPreferences(PATH,Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public long getLongValue(String key){
        if (!TextUtils.isEmpty(key)){
            return sp.getLong(key,0);
        }
        return 0;
    }
    public String getStringValue(String key){
        if (!TextUtils.isEmpty(key)){
            return sp.getString(key,null);
        }
        return null;
    }
    public int getIntValue(String key){
        if (!TextUtils.isEmpty(key)){
            return sp.getInt(key,0);
        }
        return 0;
    }
    public float getFloatValue(String key){
        if (!TextUtils.isEmpty(key)){
            return sp.getFloat(key,0);
        }
        return 0;
    }
    public boolean getBooleanValue(String key){
        if (!TextUtils.isEmpty(key)){
            return sp.getBoolean(key,false);
        }
        return true;
    }
    public void putStringValue(String key,String value){
        if (!TextUtils.isEmpty(key)){
            editor = sp.edit();
            editor.putString(key,value);
            editor.commit();
        }
    }
    public void putIntValue(String key,int value){
        if (!TextUtils.isEmpty(key)){
            editor = sp.edit();
            editor.putInt(key,value);
            editor.commit();
        }
    }
    public void putFloatValue(String key,Float value){
        if (!TextUtils.isEmpty(key)){
            editor = sp.edit();
            editor.putFloat(key,value);
            editor.commit();
        }
    }
    public void putLongValue(String key,Long value){
        if (!TextUtils.isEmpty(key)){
            editor = sp.edit();
            editor.putLong(key,value);
            editor.commit();
        }
    }
    public void putBooleanValue(String key,boolean value){
        if (!TextUtils.isEmpty(key)){
            editor = sp.edit();
            editor.putBoolean(key,value);
            editor.commit();
        }
    }

}
