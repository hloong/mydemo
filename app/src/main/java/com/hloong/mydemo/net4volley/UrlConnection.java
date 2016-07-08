package com.hloong.mydemo.net4volley;

import android.content.Context;

/**
 * 网络连接，所有网络请求方法集合
 * 
 * @author hloong
 */
public class UrlConnection {
    static Context mContext;

    String tag;

    public static UrlConnection mUrlConnection = null;

    public static UrlConnection getInstance(Context context) {
        mContext = context;
        if (mUrlConnection == null) {
            mUrlConnection = new UrlConnection();
        }
        return mUrlConnection;
    }

    /**
     * demo
     * 
     * @param apiCallBack
     * @param 最后默认的false表示格式化解析
     *            ，如果为true表示自己需要解析json
     * @param TAG
     */
//    public void getCheckVerifyRequest(ApiCallBack apiCallBack) {
//        CheckVerifyRequest mRequest = new CheckVerifyRequest();
//        mRequest.setMethodName(UrlConstants.check_verify);
//        ApiManager.executeTask(mContext, mRequest, apiCallBack, false, "Check_Verify", false, true);
//    }
//

}
