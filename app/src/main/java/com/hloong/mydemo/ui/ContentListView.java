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
 * TODO: document your custom view class.
 */
public class ContentListView extends View {
    //当前手指滑动到的位置
    private int choosedPosition = -1;
    //画文字的画笔
    private Paint paint;
    //右边的所有文字
    private String[] contentList = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    //页面正中央的TextView，用来显示手指当前滑动到的位置的文本
    private TextView textViewDialog;

    //接口变量，该接口主要用来实现当手指在右边的滑动控件上滑动时ListView能够跟着滚动
    private UpdateListView updateListView;

    public ContentListView(Context context) {
        super(context);
    }

    public ContentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(24);
    }

    public void setTextViewDialog(TextView textViewDialog){
        this.textViewDialog = textViewDialog;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int textHeight = getHeight()/contentList.length;
        for (int i = 0; i < contentList.length; i++) {
            if (i == choosedPosition){
                paint.setColor(Color.RED);
            }else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawText(contentList[i],(getWidth() - paint.measureText(contentList[i])) / 2, (i + 1) * textHeight, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int textHeight = getHeight() / contentList.length;
        float y = event.getY();
        int currentPosition = (int)(y/textHeight);
        String content = contentList[currentPosition];
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if (textViewDialog != null){
                    textViewDialog.setVisibility(View.GONE);
                }
                break;
            default:
                setBackgroundColor(Color.parseColor("#cccccc"));
                if (currentPosition > -1 && currentPosition < contentList.length){
                    if (textViewDialog != null){
                        textViewDialog.setVisibility(View.VISIBLE);
                        textViewDialog.setText(content);
                    }
                    if (updateListView != null){
                        updateListView.updateListView(content);
                    }
                    choosedPosition = currentPosition;
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setUpdateListView(UpdateListView updateListView) {
        this.updateListView = updateListView;
    }
    public interface UpdateListView{
        public void updateListView(String currentChar);
    }

    public void updateContentListView(int currentChar){
        for (int i = 0; i < contentList.length; i++) {
            if (currentChar == contentList[i].charAt(0)) {
                choosedPosition = i;
                invalidate();
                break;
            }
        }
    }

}
