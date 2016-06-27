package com.hloong.mydemo.util;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/27.
 */
public interface RequestCallBack {

    int HTTPSTATUSERROR = 0;
    int HTTPRESPONSEERROR = 1;
    int OUTOFMEMORYERROR = 2;

    void onRequestStart();

    void onRequestError(int errorCode, String errorMessage);

    void onRequestSuccess(String successMessage);

    void onRequestSuccess(InputStream successStream);

    void onRequestLoading();

    void onCancel();
}

