package com.hloong.mydemo.universal;

/**
 * Created by Administrator on 2016/6/21.
 */
public class AppNet {
    private static AppNet ourInstance = new AppNet();

    public static AppNet getInstance() {
        return ourInstance;
    }

    private AppNet() {
    }
}
