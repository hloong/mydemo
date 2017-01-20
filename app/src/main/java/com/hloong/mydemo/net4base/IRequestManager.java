package com.hloong.mydemo.net4base;

/**
 * Created by hloong on 2017/1/19.
 * 基础http请求接口
 */

public interface IRequestManager {
    void get(String url,IRequestCallback requestCallback);
    void post(String url,String requestBodyJson,IRequestCallback requestCallback);
    void put(String url,String requestBodyJson,IRequestCallback requestCallback);
    void delete(String url,String requestBodyJson,IRequestCallback requestCallback);
}
