package com.hloong.mydemo.image4base;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by hloong on 2017/1/19.
 * 初始图片加载接口
 */

public interface IimageListener {
    void display(Context context, ImageView imageView,String url,int progressId,int errorId,Object tag);
    void display(Context context, ImageView imageView,String url,int progressId,int errorId);
    void display(Context context, ImageView imageView,String url,int progressId);
    void display(Context context, ImageView imageView,String url);
    void display(Context context, ImageView imageView,Uri uri);
}
