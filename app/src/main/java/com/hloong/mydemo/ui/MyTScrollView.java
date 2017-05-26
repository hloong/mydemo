package com.hloong.mydemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by hloong on 2017/5/26.
 */

public class MyTScrollView extends ScrollView{
    public MyTScrollView(Context context) {
        super(context);
    }

    public MyTScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //分发不拦截
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
