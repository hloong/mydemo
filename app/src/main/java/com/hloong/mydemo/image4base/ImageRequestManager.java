package com.hloong.mydemo.image4base;

/**
 * Created by hloong on 2017/1/19.
 */

public class ImageRequestManager {
    public static final String type_Glide="Glide";
    public static final String type_Picasso="Picasso";
    public static final String type_default=type_Glide;

    private ImageRequestManager(){

    }

    public static IimageListener getRequest(){
        return getRequest(type_default);
    }

    private static IimageListener getRequest(String type) {
        switch (type){
            case type_Glide:
                return new GlideRequest();
            case type_Picasso:
                return new PicassoRequest();
            default:
                return new GlideRequest();
        }
    }

}
