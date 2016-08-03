package com.hloong.mydemo.net4okhttp;

import android.content.Context;

import com.hloong.mydemo.util.LogUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ProgressSubscriber<T> extends Subscriber<T>{
    private SubscriberOnNextListener listener;
    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener listener,Context context){
        this.listener = listener;
        this.context = context;
    }
    @Override
    public void onCompleted() {
        LogUtil.d("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.d("onError");
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
        LogUtil.d("onNext");

    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d("onStart");
    }
}
