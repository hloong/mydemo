package com.hloong.mydemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hl on 16/7/25.
 */
public class FlowLayout extends FrameLayout {


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //warp_content
        int width = 0;
        int height = 0;
        //记录每一行的宽度与高度
        int lineWidth = 0;
        int lineHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > sizeWidth){//换行
                width = Math.max(width,lineWidth);
                lineWidth = childWidth;//换行后，
                //行高
                height = height + lineHeight;
                lineHeight = childHeight;
            }else {
                lineWidth = lineWidth + childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            //最后一个控件
            if (i == count){
                width = Math.max(lineWidth,width);
                height = height + lineHeight;
            }
        }

        //warp_content
            setMeasuredDimension(
                    modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
                    modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //所有的view
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    //每一行的高度
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mAllViews.clear();
        mLineHeight.clear();
        //当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> lineViews = new ArrayList<View>();

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            //需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width){

                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);

                //重置我们的行宽和行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置view集合
                lineViews = new ArrayList<View>();
            }

            //行数
            int lineNum = mAllViews.size();

        }

        super.onLayout(changed, left, top, right, bottom);
    }



}
