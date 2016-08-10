package com.hloong.mydemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by hl on 16/8/10.
 */
public class CustomRelativeLayout extends RelativeLayout {
    private DragLayout dl;
    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setDragLayout(DragLayout dl){
        this.dl = dl;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (dl.getStatus() != DragLayout.Status.Close){
            return true;
        }
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dl.getStatus() != DragLayout.Status.Close){
            if (event.getAction() == MotionEvent.ACTION_UP){
                dl.close();
            }
        }
        return super.onTouchEvent(event);
    }
}
