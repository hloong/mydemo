package com.hloong.mydemo.util;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ACrash implements Thread.UncaughtExceptionHandler{
    private static final String TAG="ACrash";
    private static final int TYPE_SAVE_SDCARD=1; //崩溃日志保存本地SDCard  --建议开发模式使用
    private static final int TYPE_SAVE_REMOTE=2; //崩溃日志保存远端服务器 --建议生产模式使用

    private int type_save=2;  //崩溃保存日志模式 默认为2，采用保存Web服务器
    private static final String CRASH_SAVE_SDPATH="sdcard/hloong_cache/"; //崩溃日志SD卡保存路径
    private static final String CARSH_LOG_DELIVER="http://img2.xxh.cc:8080/SalesWebTest/CrashDeliver";
    private static ACrash instance = new ACrash();
    private Context mContext;

    private ACrash(){

    }
    public static ACrash getInstance(){
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
    }
    public void setCustomCrashInfo(Context pContext) {
        this.mContext = pContext;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    /**
     * 保存异常信息到sdcard中
     *
     * @param pContext
     * @param ex
     *            异常信息对象
     */
    private void saveToSdcard(Context pContext, Throwable ex) {
        String fileName = null;
        StringBuffer sBuffer = new StringBuffer();
        // 添加异常信息
        sBuffer.append(getExceptionInfo(ex));
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File file1 = new File(CRASH_SAVE_SDPATH);
            if (!file1.exists()) {
                file1.mkdir();
            }
            fileName = file1.toString() + File.separator + paserTime(System.currentTimeMillis()) + ".log";
            File file2 = new File(fileName);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file2);
                fos.write(sBuffer.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 进行把数据投递至服务器
     * @param pContext
     * @param ex  崩溃异常
     */
    private void saveToServer(Context pContext,Throwable ex){
        final String carsh_log=getExceptionInfo(ex);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> params=new HashMap<String,String>();
                params.put("crash_log",carsh_log);
                String result= IoUtils.responseFromServiceByGetNo(CARSH_LOG_DELIVER, params);
                if (result.equals("1")){
                    Log.d(TAG,"崩溃日志投递成功...");
                }else {
                    Log.d(TAG,"崩溃日志投递失败...");
                }
            }
        }).start();
    }
    /**
     * 获取并且转化异常信息
     * 同时可以进行投递相关的设备，用户信息
     * @param ex
     * @return 异常信息的字符串形式
     */
    private String getExceptionInfo(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("---------Crash Log Begin---------\n");
        //在这边可以进行相关设备信息投递--这边就稍微设置几个吧
        //其他设备和用户信息大家可以自己去扩展收集上传投递
//        stringBuffer.append("SystemVersion:"+ StrUtils.getLocalSystemVersion()+"\n");
        stringBuffer.append(sw.toString()+"\n");
        stringBuffer.append("---------Crash Log End---------\n");
//        return stringBuffer.toString();
        return SystemInfoUtil.getBuildInfo();
    }
    /**
     * 将毫秒数转换成yyyy-MM-dd-HH-mm-ss的格式
     * @param milliseconds
     * @return
     */
    private String paserTime(long milliseconds) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String times = format.format(new Date(milliseconds));

        return times;
    }
    /**
     * 进行弹出框提示
     *
     * @param pContext
     * @param msg
     */
    private void showToast(final Context pContext, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(pContext, msg, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }
}
