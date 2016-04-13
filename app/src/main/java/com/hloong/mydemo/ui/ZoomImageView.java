package com.hloong.mydemo.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/4/14.
 * http://blog.csdn.net/lmj623565791/article/details/39480503
 */
public class ZoomImageView extends ImageView {

    private static final String TAG = ZoomImageView.class.getSimpleName();
    public static final float SCALE_MAX = 4.0f;

    private float initScale = 1.0f;

    private final float[] matrixValues = new float[9];

    private boolean once = true;

    /**
     * 缩放的手势检测
     *
     * @param context
     */
    private ScaleGestureDetector mScaleGestureDetector = null;
    private final Matrix mScaleMatrix = new Matrix();
    /**
     * 用于双击检测
     */
    private GestureDetector mGestureDetector;
    private boolean isAutoScale;

    private int mTouchSlop;

    private float mLastX;
    private float mLastY;

    private boolean isCanDrag;
    private int lastPointerCount;

    private boolean isCheckTopAndBottom = true;
    private boolean isCheckLeftAndRight = true;
    public ZoomImageView(Context context) {
        super(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setScaleType(ScaleType.MATRIX);
        initView(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = getScaleX();
                float scaleFactor = detector.getScaleFactor();
                if (getDrawable() == null) {
                    return true;
                }
                /**
                 * 缩放的范围控制
                 */
                if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)) {
                    //最大最小值判断
                    if (scaleFactor * scale < initScale) {
                        scaleFactor = initScale / scale;
                    }
                    if (scaleFactor * scale > SCALE_MAX) {
                        scaleFactor = SCALE_MAX / scale;
                    }
                    //设置缩放比例
                    mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusX());
                    checkBorderAndCenterScale();
                    setImageMatrix(mScaleMatrix);
                }
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }

        });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScaleGestureDetector.onTouchEvent(event);
                float x = 0, y = 0;
                // 拿到触摸点的个数
                final int pointerCount = event.getPointerCount();
                // 得到多个触摸点的x与y均值
                for (int i = 0; i < pointerCount; i++) {
                    x += event.getX(i);
                    y += event.getY(i);
                }
                x = x / pointerCount;
                y = y / pointerCount;

                /**
                 * 每当触摸点发生变化时，重置mLasX , mLastY
                 */
                if (pointerCount != lastPointerCount) {
                    isCanDrag = false;
                    mLastX = x;
                    mLastY = y;
                }


                lastPointerCount = pointerCount;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "ACTION_MOVE");
                        float dx = x - mLastX;
                        float dy = y - mLastY;

                        if (!isCanDrag) {
                            isCanDrag = isCanDrag(dx, dy);
                        }
                        if (isCanDrag) {
                            RectF rectF = getMaxtrixRectF();
                            if (getDrawable() != null) {
                                isCheckLeftAndRight = isCheckTopAndBottom = true;
                                // 如果宽度小于屏幕宽度，则禁止左右移动
                                if (rectF.width() < getWidth()) {
                                    dx = 0;
                                    isCheckLeftAndRight = false;
                                }
                                // 如果高度小雨屏幕高度，则禁止上下移动
                                if (rectF.height() < getHeight()) {
                                    dy = 0;
                                    isCheckTopAndBottom = false;
                                }
                                mScaleMatrix.postTranslate(dx, dy);
                                checkMatrixBounds();
                                setImageMatrix(mScaleMatrix);
                            }
                        }
                        mLastX = x;
                        mLastY = y;
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "ACTION_UP");
                        lastPointerCount = 0;
                        break;
                }

                return true;
            }

        });
    }

    /**
     * 移动时，进行边界判断，主要判断宽或高大于屏幕的
     */
    private void checkMatrixBounds() {
        RectF rect = getMaxtrixRectF();
        float deltaX = 0, deltaY = 0;
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        if (rect.top > 0 && isCheckTopAndBottom) {
            deltaY = viewHeight - rect.bottom;
        }
        if (rect.left > 0 && isCheckLeftAndRight) {
            deltaX = -rect.left;
        }
        if (rect.right < viewWidth && isCheckLeftAndRight) {
            deltaX = viewWidth - rect.right;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 是否是推动行为
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isCanDrag(float dx, float dy) {
        return Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
    }

    private void checkBorderAndCenterScale() {
        RectF rect = getMaxtrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        if (rect.width() >= width) {
            if (rect.left > 0) {
                deltaX = -rect.left;
            }
            if (rect.right < width) {
                deltaX = width - rect.right;
            }
        } else {
            deltaX = width * 0.5f - rect.right + 0.5f * rect.width();
        }

        if (rect.height() >= height) {
            if (rect.top > 0) {
                deltaY = -rect.top;
            }
            if (rect.bottom < height) {
                deltaY = height - rect.bottom;
            }
        } else {
            deltaY = height * 0.5f - rect.bottom + 0.5f * rect.height();
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }
    /**
     * 根据当前图片的Matrix获得图片的范围
     * @return
     */
    private RectF getMaxtrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (d != null) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }

    public final float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

}
