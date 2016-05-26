package com.hloong.mydemo.image;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/5/26.
 */
public class DoubleCache implements ImageCache{
    MemoryCache mMemoryCache = new MemoryCache();
    DiskCache mDiskCache = new DiskCache();
    //先从内存缓存中获取图片，如果没有，再从SD卡中获取
    @Override
    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return  bitmap;
    }
    //将图片缓存到内存和SD卡中
    @Override
    public void put(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }
}
