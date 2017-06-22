package com.hloong.mydemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by hloong on 2017/5/26.
 */

public class myviewpager extends ViewGroup {
    public myviewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public myviewpager(Context context) {
        super(context);
    }

}
