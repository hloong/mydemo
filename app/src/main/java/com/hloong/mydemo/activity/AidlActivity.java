//package com.hloong.mydemo.activity;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.view.View;
//
//import com.hloong.mydemo.BaseActivity;
//import com.hloong.mydemo.R;
//import com.hloong.mydemo.aidl.SsoAuth;
//import com.hloong.mydemo.util.LogUtil;
//
//public class AidlActivity extends BaseActivity {
//    SsoAuth ssoAuth;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_aidl);
//        findViewById(R.id.btn_sso).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ssoAuth == null){
//                    LogUtil.d("ssoAuth== null");
//                    bindSsoService();
//                }else {
//                    doSsoAuth();
//                }
//            }
//        });
//    }
//
//    ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            ssoAuth = SsoAuth.Stub.asInterface(service);
//            doSsoAuth();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            ssoAuth = null;
//        }
//    };
//    private void doSsoAuth(){
//        try{
//            //此处执行的是server端的ssoAuth函数
//            ssoAuth.ssoAuth("hloong","123456");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//    private void bindSsoService(){
//        Intent intent = new Intent("mydemo.aild.MyService");
//        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(mConnection);
//    }
//}
