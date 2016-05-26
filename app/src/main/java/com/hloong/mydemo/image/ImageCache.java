package com.hloong.mydemo.image;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface ImageCache {
     void put(String url,Bitmap bitmap);
     Bitmap get(String url);
}
