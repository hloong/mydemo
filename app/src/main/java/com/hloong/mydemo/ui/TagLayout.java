package com.hloong.mydemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hloong on 2017/5/24.
 */

public class TagLayout extends ViewGroup {

    List<Integer> lineHeights = new ArrayList<>();
    List<List<View>> views = new ArrayList<>();

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        List<View> lineViews = new ArrayList<>();
        views.clear();
        int width = getMeasuredWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        //计算
        int childCount = getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (childWidth + lp.leftMargin+lp.rightMargin+lineWidth > width){
                //换行,另起一行
                lineHeights.add(lineHeight);
                views.add(lineViews);
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }
            lineHeight = Math.max(lineHeight,childHeight + lp.topMargin+ lp.bottomMargin);
            lineViews.add(child);
        }
        //2 摆放
        int left = 0;
        int top = 0;
        int size = views.size();
        for (int i = 0; i < size; i++) {
            lineViews = views.get(i);
            lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc +child.getMeasuredWidth();
                int bc = tc +child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);
                left += child.getMeasuredWidth() + lp.leftMargin +lp.rightMargin;

            }
            left = 0;
            top += lineHeight;
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin+lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth +childWidth > sizeWidth){
                lineWidth = childWidth;
                lineHeight = childHeight;
                width = Math.max(lineWidth,width);
            }else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            if (i == childCount -1){
                width = Math.max(width,lineWidth);
                height += lineHeight;
            }

        }
        //2,测量定义自身大小
        int measureWidth = (modeWidth == MeasureSpec.EXACTLY)?sizeWidth:width;
        int measureHeight = (modeHeight == MeasureSpec.EXACTLY)?sizeHeight:height;
        setMeasuredDimension(measureWidth,measureHeight);
    }
}
