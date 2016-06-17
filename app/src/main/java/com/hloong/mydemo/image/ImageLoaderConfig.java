package com.hloong.mydemo.image;

/**
 * 通用的图片缓存加载
 * Created by Administrator on 2016/6/16.
 */
public class ImageLoaderConfig {
    //图片缓存配置对象
    ImageCache bitmapCache = new MemoryCache();
    //加载策略

    int threadCount = Runtime.getRuntime().availableProcessors() + 1;
    private ImageLoaderConfig(){

    }

    public static class Builder{

        ImageCache imageCache = new MemoryCache();

    }
}
