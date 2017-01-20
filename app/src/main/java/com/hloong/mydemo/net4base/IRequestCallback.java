package com.hloong.mydemo.net4base;

/**
 * Created by hloong on 2017/1/17.
 */

public interface IRequestCallback {
    void onSuccess(String response);
    void onFailure(Throwable throwable);
}
