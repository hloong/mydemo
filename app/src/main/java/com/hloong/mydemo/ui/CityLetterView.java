package com.hloong.mydemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hloong on 2017/2/14.
 */

public class CityLetterView extends View {

    private Paint mPaint;
    private boolean isShowBg = false;
    private int choose = -1;
    private TextView mTvDialog;
    private OnSlidingListener mOnSlidingListener;
    private String[] letter={
            "定位", "最近", "热门", "全部", "A", "B", "C", "D",
            "E", "F", "G", "H","J", "K", "L", "M", "N","P", "Q",
            "R", "S", "T","W", "X", "Y", "Z"
    };

    public CityLetterView(Context context) {
        super(context);
    }
    public CityLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    public CityLetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(26);
        mPaint.setColor(Color.parseColor("#8c8c8c"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowBg){
            canvas.drawColor(Color.parseColor("#40000000"));
        }
        float singleHeight = getHeight() / letter.length;
        int width = getWidth();
        for (int i = 0; i < letter.length; i++) {
            String text = letter[i];
            float xPos = width / 2 - mPaint.measureText(text) / 2;
            float yPos = singleHeight * i + singleHeight;
            //通过不断的改变yPos将数组中的数据一个一个绘制到自定义的View中
            canvas.drawText(text,xPos,yPos,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int position =(int)( event.getY() / getHeight() * letter.length);
        int oldChoose = choose;
        switch (action){
            case MotionEvent.ACTION_DOWN:
                isShowBg = true;
                if (oldChoose != position && mOnSlidingListener != null) {
                    if (position > 0 && position < letter.length) {
                        //将滑动到的字母传递到activity中
                        mOnSlidingListener.sliding(letter[position]);
                        choose=position;
                        if(mTvDialog!=null){
                            mTvDialog.setVisibility(View.VISIBLE);
                            mTvDialog.setText(letter[position]);
                        }
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isShowBg = true;
                if (oldChoose != position && mOnSlidingListener != null){
                    if (position >= 0 && position < letter.length){
                        mOnSlidingListener.sliding(letter[position]);
                        choose = position;
                        if (mTvDialog != null){
                            mTvDialog.setVisibility(View.VISIBLE);
                            mTvDialog.setText(letter[position]);
                        }
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isShowBg = false;
                choose=-1;
                if(mTvDialog!=null){
                    mTvDialog.setVisibility(View.GONE);
                }
                invalidate();
                break;
        }
        return true;
    }

    public void setOnSlidingListener(OnSlidingListener mOnSlidingListener){
        this.mOnSlidingListener = mOnSlidingListener;
    }
    public interface OnSlidingListener{
        public void sliding(String str);
    }
    public void setTextView(TextView tvDialog){
        mTvDialog = tvDialog;
    }

}
