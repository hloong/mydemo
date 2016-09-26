package com.hloong.mydemo.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.hloong.mydemo.R;

import java.util.ArrayList;
import java.util.List;

public class LeakCanaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        //只有了目前的activity引用，跳转到现在这个acivity后就返回，等一会就有一个内存泄漏的notification出现
        ActivityMgr.getInstance().addActivity(this);
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                return null;
            }
        };
    }


}
class ActivityMgr{
    private static ActivityMgr instance = new ActivityMgr();
    List<Activity> activities = new ArrayList<Activity>();

    private ActivityMgr(){

    }

    public static ActivityMgr getInstance(){
        return instance;
    }

    public void  addActivity(Activity activity){
        activities.add(activity);
    }
}