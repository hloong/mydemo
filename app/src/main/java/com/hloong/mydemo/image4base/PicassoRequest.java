package com.hloong.mydemo.image4base;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by hloong on 2017/1/19.
 */

public class PicassoRequest implements IimageListener {
    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId, Object tag) {
        Picasso.with(context).load(url).placeholder(progressId).error(errorId).tag(tag).into(imageView);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId) {
        Picasso.with(context).load(url).placeholder(progressId).error(errorId).into(imageView);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId) {
        Picasso.with(context).load(url).placeholder(progressId).into(imageView);
    }

    @Override
    public void display(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }

    @Override
    public void display(Context context, ImageView imageView, Uri uri) {
        Picasso.with(context).load(uri).into(imageView);
    }
}
