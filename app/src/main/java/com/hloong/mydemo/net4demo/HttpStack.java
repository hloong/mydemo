package com.hloong.mydemo.net4demo;
/**
 * 执行网络请求的接口
 *
 * @author hloong
 */
public interface HttpStack {
    /**
     * 执行Http请求
     *
     * @param request 待执行的请求
     * @return
     */
    public Response performRequest(Request<?> request);
}