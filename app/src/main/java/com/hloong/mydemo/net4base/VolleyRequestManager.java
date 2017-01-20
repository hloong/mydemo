package com.hloong.mydemo.net4base;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hloong on 2017/1/19.
 */

public class VolleyRequestManager implements IRequestManager {
    private static VolleyRequestManager instance = new VolleyRequestManager();

    public static VolleyRequestManager getInstance() {
        return instance;
    }

    private VolleyRequestManager() {
    }

    @Override
    public void get(String url, final IRequestCallback requestCallback) {
        StringRequest request = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                     public void onResponse(String response) {
                        requestCallback.onSuccess(response);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestCallback.onFailure(error);
                    }
                }
        );
//        HttpRequestExampleApp.mQueue.add(request);
    }

    @Override
    public void post(String url, String requestBodyJson, IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.POST);
    }

    @Override
    public void put(String url, String requestBodyJson, IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.PUT);
    }

    @Override
    public void delete(String url, String requestBodyJson, IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.DELETE);
    }

    private void requestWithBody(String url,String requestBodyJson,final IRequestCallback requestCallback,int method){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(requestBodyJson);
        } catch (JSONException e) {
            e.printStackTrace();
            //// TODO: 2016/12/11 异常处理
        }
        JsonRequest<JSONObject> request = new JsonObjectRequest(method, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestCallback.onSuccess(response != null ? response.toString() : null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestCallback.onFailure(error);
                    }
                });
    }
}
