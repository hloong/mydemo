package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.EventBusTest;
import com.hloong.mydemo.bean.EventBusTest1;
import com.hloong.mydemo.bean.EventBusTest2;
import com.hloong.mydemo.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusActivity extends BaseActivity {

    @BindView(R.id.tv_msg) TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusTest event){
        tv_msg.setText(event.getMsg());
        Toast.makeText(EventBusActivity.this,event.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(EventBusTest1 event){
        LogUtil.d(event.getMsg());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent2(EventBusTest2 event){
        LogUtil.d(event.getMsg());
    }




    @OnClick(R.id.btn_goto)
    void onClick(){
        openActivity(EventBus2Activity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
