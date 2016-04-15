package com.hloong.mydemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.hloong.mydemo.R;

/**
 * TODO: document your custom view class.
 */
public class PointView extends View {

    public int pointW = 20;
    public void setPointW(int pointW){
        this.pointW = pointW;
    }
    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,pointW,paint);
    }

}
