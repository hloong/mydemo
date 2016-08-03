package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.EventBusTest;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class EventBus2Activity extends BaseActivity {
    @BindView(R.id.tv_msg)
    TextView tv_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);

    }
    @OnClick(R.id.btn_goto)
    void onClick(){
        EventBus.getDefault().post(new EventBusTest("这是第二个activity回传的消息"));
        this.finish();
    }
}
