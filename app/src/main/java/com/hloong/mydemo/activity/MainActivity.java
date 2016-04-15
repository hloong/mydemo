package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.widget.MyPointDialog;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView(R.id.btn_multi_list).setOnClickListener(l);
        getView(R.id.btn_multi).setOnClickListener(l);
        getView(R.id.btn_simple_list).setOnClickListener(l);
        getView(R.id.btn_list_gridview).setOnClickListener(l);
        getView(R.id.btn_bar_code).setOnClickListener(l);
        getView(R.id.btn_two_side_circle).setOnClickListener(l);
        getView(R.id.btn_form).setOnClickListener(l);
        getView(R.id.btn_chenjinshi).setOnClickListener(l);
        getView(R.id.btn_scale).setOnClickListener(l);
        getView(R.id.btn_photo).setOnClickListener(l);
    }
    
    
    
    OnClickListener l = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
            case R.id.btn_multi:
                openActivity(MultiListActivity.class);
                break;
            case R.id.btn_multi_list:
                openActivity(ChatActivity.class);
                break;
            case R.id.btn_simple_list:
                openActivity(SimpleListActivity.class);
                break;
            case R.id.btn_list_gridview:
                openActivity(ListGridViewActivity.class);
                break;
            case R.id.btn_bar_code:
                break;
            case R.id.btn_two_side_circle:
                openActivity(CircleBarActivity.class);
                break;
            case R.id.btn_form:
                openActivity(GraphViewActivity.class);
                break;
            case R.id.btn_chenjinshi:
                openActivity(ChenJinShiActivity.class);
                break;
            case R.id.btn_scale:
                openActivity(ScaleActivity.class);
                break;
            case R.id.btn_photo:
                openActivity(PhotoActivity.class);
                break;
            default:
                break;
            }
        }
    };
}
