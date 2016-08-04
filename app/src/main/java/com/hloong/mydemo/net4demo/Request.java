package com.hloong.mydemo.net4demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求类. 注意GET和DELETE不能传递请求参数,因为其请求的性质所致,用户可以将参数构建到url后传递进来到Request中.
 * @param <T> T为请求返回的数据类型
 * Created by hloong on 2016/6/16.
 */
public abstract class Request<T> implements Comparable<Request<T>>{
    /**
     * http请求方法枚举，这里我们只有GET，POST，PUT，DELETE四种
     *
     */
    public static enum HttpMethod{
        GET("GET"),POST("POST"),PUT("PUT"),DELETE("DELETE");

        private String mHttpMethod = "";
        private HttpMethod(String method){
            mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }
    }

    public static enum Priority{
        LOW,NORMAL,HIGN,IMMEDIATE
    }

    /**
     * Default encoding for POST or PUT parameters. See
     * {@link #getParamsEncoding()}.
     */
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    /**
     * Default Content-type
     */
    public final static String HEADER_CONTENT_TYPE = "Content-Type";
    /**
     * 请求序列号
     */
    protected int mSerialNum = 0;
    /**
     * 优先级默认设置为Normal
     */
    protected Priority mPriority = Priority.NORMAL;
    /**
     * 是否取消该请求
     */
    protected boolean isCancel = false;

    /** 该请求是否应该缓存 */
    private boolean mShouldCache = true;

    /**
     * 请求Listener
     */
    protected RequestListener<T> mRequestListener;
    /**
     * 请求的url
     */
    private String mUrl = "";



    /**
     * 请求的方法
     */
    HttpMethod mHttpMethod = HttpMethod.GET;
    /**
     * 请求的header
     */
    private Map<String, String> mHeaders = new HashMap<String, String>();
    /**
     * 请求参数
     */
    private Map<String, String> mBodyParams = new HashMap<String, String>();

    /**
     * @param method
     * @param url
     * @param listener
     */
    public Request(HttpMethod method, String url, RequestListener<T> listener) {
        mHttpMethod = method;
        mUrl = url;
        mRequestListener = listener;
    }

    public void addHeader(String name, String value) {
        mHeaders.put(name, value);
    }

    /**
     * 从原生的网络请求中解析结果,子类覆写
     *
     * @param response
     * @return
     */
    public abstract T parseResponse(Response response);
    /**
     * 处理Response,该方法运行在UI线程.
     *
     * @param response
     */
    public final void deliveryResponse(Response response){
        T result = parseResponse(response);
        if (null != mRequestListener){
            int code = response != null ? response.getCode() : -1;
            String msg = response != null ? response.getMsg():"unkown error";
            mRequestListener.onComplete(code,result,msg);
        }
    }

    public RequestListener<T> getRequestListener() {
        return mRequestListener;
    }
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getSerialNum() {
        return mSerialNum;
    }

    public void setSerialNum(int mSerialNum) {
        this.mSerialNum = mSerialNum;
    }

    public Priority getPriority() {
        return mPriority;
    }

    public void setPriority(Priority mPriority) {
        this.mPriority = mPriority;
    }

    public boolean isShouldCache() {
        return mShouldCache;
    }

    public void setShouldCache(boolean mShouldCache) {
        this.mShouldCache = mShouldCache;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public HttpMethod getHttpMethod() {
        return mHttpMethod;
    }
    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }
    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public Map<String, String> getParams() {
        return mBodyParams;
    }


    public void cancel() {
        isCancel = true;
    }

    public boolean isCanceled() {
        return isCancel;
    }

    public boolean isHttps() {
        return mUrl.startsWith("https");
    }

    /**
     * 返回POST或者PUT请求时的Body参数字节数组
     *
     */
    public byte[] getBody() {
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }
    /**
     * 将参数转换为Url编码的参数串
     */
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    // 用于对请求的排序处理,根据优先级和加入到队列的序号进行排序
    @Override
    public int compareTo(Request<T> another) {
        Priority priority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        // 如果优先级相等,那么按照添加到队列的序列号顺序来执行
        return priority.equals(another) ? this.getSerialNum() - another.getSerialNum()
                : priority.ordinal() - anotherPriority.ordinal();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mHeaders == null) ? 0 : mHeaders.hashCode());
        result = prime * result + ((mHttpMethod == null) ? 0 : mHttpMethod.hashCode());
        result = prime * result + ((mBodyParams == null) ? 0 : mBodyParams.hashCode());
        result = prime * result + ((mPriority == null) ? 0 : mPriority.hashCode());
        result = prime * result + (mShouldCache ? 1231 : 1237);
        result = prime * result + ((mUrl == null) ? 0 : mUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request<?> other = (Request<?>) obj;
        if (mHeaders == null) {
            if (other.mHeaders != null)
                return false;
        } else if (!mHeaders.equals(other.mHeaders))
            return false;
        if (mHttpMethod != other.mHttpMethod)
            return false;
        if (mBodyParams == null) {
            if (other.mBodyParams != null)
                return false;
        } else if (!mBodyParams.equals(other.mBodyParams))
            return false;
        if (mPriority != other.mPriority)
            return false;
        if (mShouldCache != other.mShouldCache)
            return false;
        if (mUrl == null) {
            if (other.mUrl != null)
                return false;
        } else if (!mUrl.equals(other.mUrl))
            return false;
        return true;
    }

    public static interface RequestListener<T>{
        /**
         * 请求完成后的回调
         */
        public void onComplete(int code,T response,String msg);

    }
}
