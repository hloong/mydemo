package com.hloong.mydemo.net4volley;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hloong.mydemo.Session;
import com.hloong.mydemo.util.LogUtil;
import com.hloong.mydemo.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;


/**
 * api请求管理
 * @author Administrator
 *对数据的请求和返回的数据处理
 *智能判断是否成功 根据配置判断是或否显示提示信息和对话框
 */
public class ApiManager {
    private ApiCallBack mApiCallBack;
    private ApiRequest mRequest;
    private Context context;
    private boolean showDialog;
    private Dialog mProgress;
    private String tag="api";
    private long startTime;
    private static final long TIME_INTERVAL = 1200;
    
    /**
     * Api管理对象
     * @param context 上下文对象
     * @param mRequest 请求对象
     * @param ApiCallBack 网络线程
     * @param showDialog 网络加载dialog
     * @param tag 网络标记
     * @param post 是否POST请求
     * */
    public ApiManager(Context context, ApiRequest mRequest, ApiCallBack ApiCallBack, boolean showDialog, String tag, boolean post) {
        this.context = context;
        this.mRequest = mRequest;
        this.mApiCallBack = ApiCallBack;
        this.showDialog = showDialog;
        this.tag = tag;
        if(NetUtil.checkConnection(context)){
            if (post) {
                DoPostReQuest(mRequest, showDialog);
            } else {
                DoGetReQuest(mRequest, showDialog);
            }
        }else{
            //单例toast，弹出网络访问时无网络提示
//            ToastSingleton.showToast(context, R.string.connetionexception);
        }
    }
    
    private void showProgress() {
        try{
            startTime = System.currentTimeMillis();
            mProgress = new ProgressDialog(context);
            mProgress.setCanceledOnTouchOutside(false);
            mProgress.show();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
    private void stopProgress() {
        if(mProgress!=null&&mProgress.isShowing()&& !((Activity)context).isFinishing()){
            try {
                mProgress.cancel();
                mProgress = null;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void DoGetReQuest(final ApiRequest mApiRequest, final boolean showDialog) {
        if(showDialog) {
            showProgress();
        }
        StringRequest mRequest = new StringRequest(Method.GET, mApiRequest.toGet_Url(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String string) {
                        LogUtil.d("api_HTTP", "GET_Response-- "+mApiRequest.getMethodName()+" 获取的结果->"+string);
                        if(!TextUtils.isEmpty(string)){
                            try {
                                

                                if (string.startsWith("\ufeff")) {//服务器格式错误转换
                                    string = string.substring(1);
                                }
                                    JSONObject jsonObject = new JSONObject(string);
                                    if (jsonObject.has("data")) {
                                        Type typeOfT = ResponseTypeProvider.getApiResponseType(mApiRequest.getClass());
                                        doStuffWithResult(GsonConverter.fromJson(string, typeOfT),string);
                                    }else { 
                                        doStuffWithResult(null, string);
                                    } 
                            }  catch (Exception e) {
                                stopProgress();
                                LogUtil.e("api_HTTP", "GET_Exception-- "+mApiRequest.getMethodName()+" 获取的结果->"+e.toString());
                                if(showDialog && context!=null) { 
//                                    ToastMaster.showMiddleToast(context, R.string.dataexception);
                                }
                                e.printStackTrace();
                            };
                        }else{  
                            stopProgress();
                        }   
                        
                    }
                }, createMyReqErrorListener()) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        // TODO Auto-generated method stub
                        return Session.getInstance().getHeaders();
                    }
            
        };
        mRequest.setTag(tag);
        RequestManager.getRequestQueue().add(mRequest);
    }
    
    public void DoPostReQuest(final ApiRequest mApiRequest, final boolean showDialog) {
        if(showDialog){
            showProgress();
        }
        StringRequest mRequest = new StringRequest(Method.POST, mApiRequest.toPost_Url(), new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Log.d("api_HTTP", "POST_Response-- "+mApiRequest.getMethodName()+" 获取结果->"+ string);
                if(!TextUtils.isEmpty(string)){
                    try {
                        
                        if (string.startsWith("\ufeff")) {
                            string = string.substring(1);
                        }
                        JSONObject jsonObject = new JSONObject(string);
                        if (jsonObject.has("data")) {
                            Type typeOfT = ResponseTypeProvider.getApiResponseType(mApiRequest.getClass());
                            doStuffWithResult(GsonConverter.fromJson(string, typeOfT),string);
                        }else {
                            doStuffWithResult(null, string);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        stopProgress();
                    }
                }else{
                    stopProgress();
                }
                
            }
        }, createMyReqErrorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                //判断未登录的加密处理、1.登录接口采用云加密；2.非登录接口采用默认加密
                return mApiRequest.getValueMap(mApiRequest);  //参数用post提交给服务器
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                return Session.getInstance().getHeaders();
            }
        };
        mRequest.setTag(tag);
        RequestManager.getRequestQueue().add(mRequest);
    }
    
    private void doWithData(final ApiResponse result,final String json) {
        int code = -1;
        String msg ="";
        if (result != null) {//格式化过json
            code = result.getCode();
        }else {//需要自己处理解析
            try {
                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject.has("code")) {
                    code = jsonObject.getInt("code");
                }
                if (jsonObject.has("msg")) {
                    msg = jsonObject.getString("msg");
                    if (code != 200 && code != 10004 && code != 10000) {
                    }
                }  
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
        switch (code) {
        case 200://200   请求成功
            mApiCallBack.onResponse(mRequest, result, json);
            break;
        default:
            mApiCallBack.onError(mRequest, json);
            break;
                
        }
    }
    /**
     * 上线，屏蔽200，202以外的所有状态
     * @param result
     * @param json
     */
    private void doStuffWithResult(final ApiResponse result,final String json) {
        long endTime = System.currentTimeMillis();
        if(endTime - startTime > TIME_INTERVAL){
            if(mProgress!=null&&mProgress.isShowing()){
                try {
                    mProgress.cancel();
                    mProgress = null;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            doWithData(result, json);
        }else{
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if(mProgress!=null&&mProgress.isShowing()){
                        try {
                            mProgress.cancel();
                            mProgress = null;
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    doWithData(result, json);
                }
            }, TIME_INTERVAL - (endTime - startTime));// 延迟1秒进入
        }
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                stopProgress();
//              Log.e("LOGIN-ERROR1", error.getMessage(), error);
//                byte[] htmlBodyBytes = error.networkResponse.data;
                if (error != null && error.networkResponse!=null) {
                    Log.e("api_HTTP", "网页错误,无JSON返回-->"+ "backCode:" +error.networkResponse.statusCode);
//                     if (error.networkResponse.statusCode == 504) {
//                         ToastMaster.showMiddleToast(context, R.string.time_data_error);
//                         mApiCallBack.doStuffWithResponseErrorTime(mRequest);
//                    }else {
//                        ToastMaster.showMiddleToast(context, R.string.service_data_error);
//                    }
//                    if (error.networkResponse.statusCode >= 500 ) {
//                        //正式上线屏蔽
//                        ToastMaster.showMiddleToast(context, "专属弹出：后台问题，服务器500");
//                    }else if (error.networkResponse.statusCode==404) {
//                        ToastMaster.showMiddleToast(context, "专属弹出：后台问题，服务器404");
//                    }else if (error.networkResponse.statusCode==403) {
//                        ToastMaster.showMiddleToast(context, "专属弹出：后台问题，服务器403 Forbidden");
//                    }else if (error.networkResponse.statusCode==400) {
//                        ToastMaster.showMiddleToast(context, "专属弹出：后台问题，服务器400");
//                    }else if (error.networkResponse.statusCode >=300 && error.networkResponse.statusCode <400) {
//                        ToastMaster.showMiddleToast(context, "专属弹出：后台问题，服务器返回 处于300-400之间");
//                    }
                }
                mApiCallBack.onError(mRequest, error.networkResponse.toString());
                
                if (showDialog){
//                  ToastMaster.showMiddleToast(context, R.string.service_data_error);
                }
            }
        };
    }




    /**
     * HTTP GET
     * @param context 上下文对象
     * @param request 请求对象
     * @param apiCallBack 网络执行线程
     * @param showDialog 网络加载dialog显示
     * @param tag 网络标记
     * @return
     */
    public static ApiManager executeTask(Context context, ApiRequest request, ApiCallBack apiCallBack, boolean showDialog, String tag) {
        return  new ApiManager(context, request, apiCallBack, showDialog, tag, false);
    }

    /**
     * HTTP POST
     * @param context 上下文对象
     * @param request 请求对象
     * @param apiCallBack 网络执行线程
     * @param showDialog 网络加载dialog显示
     * @param tag 网络标记
     * @param useJson 表示要自己用Json来解析
     * @return
     */
    public static ApiManager executeTaskPost(Context context, ApiRequest request, ApiCallBack apiCallBack, boolean showDialog, String tag,boolean useJson) {
        return new ApiManager(context, request, apiCallBack, showDialog, tag, true);
    }
}
