package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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



    private void getUrl(){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://op.juhe.cn/shanghai/police?key=4ceeff4e2486d39a40df48f3118e5a9c")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }


    private void postUrl(){


        FormBody.Builder builder = new FormBody.Builder();

        Request request = new Request.Builder()
                .url("http://op.juhe.cn/shanghai/police")
                .post(builder.build())
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
