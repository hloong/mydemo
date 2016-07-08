package com.hloong.mydemo.net4volley;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ApiResponse extends ApiPacket{
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}