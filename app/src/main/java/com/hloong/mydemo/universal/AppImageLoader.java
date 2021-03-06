package com.hloong.mydemo.universal;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 图片加载框架合集
 * Created by Administrator on 2016/6/21.
 */
public class AppImageLoader {

    public static final int TYPE=1;//0表示UIL框架，1表示Fresco框架，其他的Picasso和Glide不需要初始化
    public static void init(Context context){
        switch (TYPE){
            case 0:
                initImageLoader(context);
                break;
            case 1:
                Fresco.initialize(context);
                break;
        }
    }

    /**
     * 加载UIL框架
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    /**
     * UIL图片基本加载
     * @param uri
     * @param imageView
     */
    public static void displayImage(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri,imageView);
    }

    /**
     * Picasso,Glide图片基本加载
     * @param context
     * @param uri
     * @param imageView
     */
    public static void displayImage(Context context,String uri, ImageView imageView) {
//        Picasso.with(context).load(uri).into(imageView);
        Glide.with(context).load(uri).into(imageView);
    }

    /**
     * Fresco 图片基本加载
     * @param src
     * @param draweeView
     */
    public static void displayImage(String src, SimpleDraweeView draweeView){
        Uri uri = Uri.parse(src);
        draweeView.setImageURI(uri);
    }
}
