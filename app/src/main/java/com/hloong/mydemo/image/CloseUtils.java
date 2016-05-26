package com.hloong.mydemo.image;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/14.
 */
public final class CloseUtils {
    private CloseUtils(){

    }

    /**
     * 关闭Closeable对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if (null != closeable) {
            try{
                closeable.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
