package com.hloong.mydemo.image4base;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.hloong.mydemo.R;

/**
 * Created by hloong on 2017/1/19.
 * Glide请求
 */

public class GlideRequest implements IimageListener{

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId, Object tag) {
        DrawableTypeRequest<String> load = Glide.with(context).load(url);
        if (progressId != -1){
            load.placeholder(progressId).centerCrop();
        }
        if (errorId != -1){
            load.error(errorId);
        }else {
            load.error(R.drawable.arrow_right);
        }
        load.into(imageView);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId) {
        display(context,imageView,url,progressId,errorId,null);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId) {
        display(context,imageView,url,progressId,-1,null);
    }

    @Override
    public void display(Context context, ImageView imageView, String url) {
        display(context,imageView,url,-1,-1,null);
    }

    @Override
    public void display(Context context, ImageView imageView, Uri uri) {
        DrawableTypeRequest<Uri> load = Glide.with(context).load(uri);
        load.into(imageView);
    }
}
