package com.hloong.mydemo.image;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Administrator on 2016/5/26.
 */
public class MemoryCache implements ImageCache{
    //图片LRU缓存
    LruCache<String,Bitmap> mImageCache;

    public MemoryCache(){
        initImageCache();
    }
    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一的可用内存为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String , Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }


    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
