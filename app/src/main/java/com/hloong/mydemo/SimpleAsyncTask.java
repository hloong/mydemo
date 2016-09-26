package com.hloong.mydemo;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

/**
 * Created by hloong on 2016/9/14.
 * android从小工到专家 之 AsyncTask，P106
 */
public abstract class SimpleAsyncTask<Result> {
    private static final HandlerThread ht = new HandlerThread("SimpleAsyncTask", Process.THREAD_PRIORITY_BACKGROUND);
    static{
        ht.start();
    }

    final Handler mUIHandler = new Handler(Looper.getMainLooper());
    final Handler mAsyncHandler = new Handler(ht.getLooper());

    protected void onPreExecute(){}
    protected void onPostExecute(Result result){}
    protected abstract Result doInBackground();
    public final SimpleAsyncTask<Result> execute(){
        onPreExecute();
        mAsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                postResult(doInBackground());
            }
        });
        return this;
    }

    private void postResult(final Result result){
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(result);
            }
        });
    }
}

