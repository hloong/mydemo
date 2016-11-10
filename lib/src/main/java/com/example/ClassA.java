package com.example;

/**
 * Created by hloong on 2016/11/4.
 */

public class ClassA {
    public ICallBack callBack;

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    public void doS(){
        callBack.callback();
    }
}
