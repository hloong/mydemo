package com.hloong.mydemo.widget;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hloong.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MyPointDialog extends Dialog {

    public static final int POINT_NUM = 5;
    /** 点的大小*/
    public static final int POINT_W = 10;
    /** 点组合的大小*/
    public static final int POINT_LAY = 30;
    public static final int DELAY = 100;
    private Context context;
    private LinearLayout ll;
    private List<PointView> views;
    private int mScreenW,mScreenH,dialogW,dialogH;

    public MyPointDialog(Context context){
        super(context, R.style.PointDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mypoint);
        initView();
        getPointView();
    }
    private void initView(){
        ll = (LinearLayout)findViewById(R.id.mianView);
        mScreenH = context.getResources().getDisplayMetrics().heightPixels;
        mScreenW = context.getResources().getDisplayMetrics().widthPixels;

        dialogH = (int)(mScreenH * 0.5);
        dialogW = (int)(mScreenW * 0.5) ;
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(dialogW,dialogH);
        ll.setLayoutParams(layout);

    }
    private void getPointView() {
        views = new ArrayList<>();
        LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(POINT_LAY,POINT_LAY);
        for (int i = 0; i < POINT_NUM; i++) {
            PointView pointView = new PointView(context);
            pointView.setLayoutParams(lay);
            pointView.setPointW(POINT_W);
            views.add(pointView);
            ll.addView(pointView);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            for (int i = 0; i < POINT_NUM; i++) {
                final PointView view = views.get(i);
                //计算圆点基于父控件的Y轴位置，公式为：dialog高度的一半 - 圆点父控件高度的一半
                int height = ll.getHeight() / 2 - mScreenW / POINT_W;
                ValueAnimator va = ValueAnimator.ofInt(height, height + mScreenH / 30, height - mScreenH / 30, height);
                va.setDuration(1000);//动画的持续时间
                va.setRepeatCount(Integer.MAX_VALUE);//重复播放
                va.setStartDelay(DELAY + DELAY * i);//延迟播放
                va.setInterpolator(new DecelerateInterpolator());
                va.start();
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    public void onAnimationUpdate(ValueAnimator animation) {
                        // Y轴方向变化参数
                        int parseInt = Integer.parseInt(animation.getAnimatedValue().toString());
                        view.setY(parseInt);

                        // XY轴大小变化参数
                        float fraction = animation.getAnimatedFraction();
                        view.setScaleX(fraction < 0.5 ? 1 - fraction : fraction);
                        view.setScaleY(fraction < 0.5 ? 1 - fraction : fraction);
                    }
                });
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
