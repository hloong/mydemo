package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.EventBusTest;
import com.hloong.mydemo.bean.EventBusTest1;
import com.hloong.mydemo.bean.EventBusTest2;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBus2Activity extends BaseActivity {
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_goto)
    void onClick(){
        EventBus.getDefault().post(new EventBusTest("这是第二个activity回传的消息1"));
    }
    @OnClick(R.id.btn_goto2)
    void onClick2(){
        EventBus.getDefault().post(new EventBusTest1("这是第二个activity回传的消息2"));
    }
    @OnClick(R.id.btn_goto3)
    void onClick3(){
        EventBus.getDefault().post(new EventBusTest2("这是第二个activity回传的消息3"));
    }
}
