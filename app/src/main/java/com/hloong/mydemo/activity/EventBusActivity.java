package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.EventBusTest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class EventBusActivity extends BaseActivity {

    @BindView(R.id.tv_msg) TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusTest event){
        tv_msg.setText(event.getMsg());
        Toast.makeText(EventBusActivity.this,event.getMsg(),Toast.LENGTH_LONG).show();
    }



    @OnClick(R.id.btn_goto)
    void onClick(){
        openActivity(EventBus2Activity.class);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
