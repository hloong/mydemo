package com.hloong.mydemo.net4okhttp;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
