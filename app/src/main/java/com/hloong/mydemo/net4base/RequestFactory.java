package com.hloong.mydemo.net4base;

/**
 * Created by hloong on 2017/1/20.
 */

public class RequestFactory {
    public static final String type_okhttp="OKhttp";
    public static final String type_volley="Volley";
    public static final String type_default=type_okhttp;

    private RequestFactory(){

    }
    public static IRequestManager getRequestManager(){
        return getRequest(type_default);
    }

    public static IRequestManager getRequest(String type){
        switch (type){
            case type_okhttp:
                return OkHttpRequestManager.getInstance();
            case type_volley:
                return VolleyRequestManager.getInstance();
            default:
                return OkHttpRequestManager.getInstance();
        }
    }
}
