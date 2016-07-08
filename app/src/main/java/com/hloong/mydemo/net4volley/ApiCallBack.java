package com.hloong.mydemo.net4volley;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface ApiCallBack {
    void onResponse(ApiRequest apiRequest, ApiResponse apiResponse, String result);

    void onError(ApiRequest apiRequest, String error);
}
