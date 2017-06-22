package com.hloong.mydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewStub;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.fragment.SplashFragment;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();
    private SplashFragment fragment;
    private ViewStub viewStub ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        fragment = new SplashFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment).commit();

        viewStub = (ViewStub) findViewById(R.id.content_viewstub);
        /**
         * 采用handler来做主要是解决 performTraversals() 中的ViewTree的测量，布局，绘制工作所消耗的时间
         */
        //窗体加载完毕再加载真的布局,
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewStub.inflate();
                        openActivity(MainActivity.class);
                        finish();
                    }
                });
            }
        });
        //窗体加载完毕，延迟一段时间执行动画
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new DelayRunnable(SplashActivity.this,fragment),2000);
            }
        });
    }

    static class DelayRunnable implements Runnable{
        private WeakReference<Context> context;
        private WeakReference<SplashFragment> splashFragment;

        public DelayRunnable(Context context, SplashFragment fragment){
            this.context = new WeakReference<Context>(context);
            splashFragment = new WeakReference<SplashFragment>(fragment);

        }

        @Override
        public void run() {
            if (context != null){
                SplashFragment fragment = splashFragment.get();
                if (fragment != null){
                    return;
                }
                FragmentActivity activity = (FragmentActivity)context.get();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        }
    }

}


