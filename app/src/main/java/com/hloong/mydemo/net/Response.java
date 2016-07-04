package com.hloong.mydemo.net;

import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Response extends BasicHttpResponse{
    public byte[] rawData = new byte[0];
    private int code;
    private String msg;
    public Response(StatusLine statusLine) {
        super(statusLine);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }
    public Response(StatusLine statusline, ReasonPhraseCatalog catalog, Locale locale) {
        super(statusline, catalog, locale);
    }

    public byte[] getRawData() {
        return rawData;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public void setEntity(HttpEntity entity) {
        super.setEntity(entity);
    }

    /** Reads the contents of HttpEntity into a byte[]. */
    private byte[] entityToBytes(HttpEntity entity) {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
