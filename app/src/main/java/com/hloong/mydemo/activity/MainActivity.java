package com.hloong.mydemo.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.net.MultipartEntity;
import com.hloong.mydemo.net.MultipartRequest;
import com.hloong.mydemo.net.Request.HttpMethod;
import com.hloong.mydemo.net.Request.RequestListener;
import com.hloong.mydemo.net.RequestQueue;
import com.hloong.mydemo.net.SimpleNet;
import com.hloong.mydemo.net.StringRequest;
import com.hloong.mydemo.universal.AppImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private String url = "http://op.juhe.cn/shanghai/police?key=4ceeff4e2486d39a40df48f3118e5a9c";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppImageLoader.initImageLoader(this);

        setContentView(R.layout.activity_main);
        textView =(TextView)findViewById(R.id.tv);
        textView.setOnClickListener(l);
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
                case R.id.tv:
                    sendStringRequest();
                    break;
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


    /**
     * okhttp 请求
     */
    private void getUrl(){
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
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





    // 1、构建请求队列，采用的是com.hloong.mydemo.net包下的框架来做的，此框架我已经改了只支持api9以上的了
    RequestQueue mQueue = SimpleNet.newRequestQueue();

    /**
     * 发送GET请求,返回的是String类型的数据, 同理还有{@see JsonRequest}、{@see MultipartRequest}
     */
    private void sendStringRequest() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.show();
        StringRequest request = new StringRequest(HttpMethod.GET, "http://www.baidu.com",
                new RequestListener<String>() {

                    @Override
                    public void onComplete(int stCode, String response, String errMsg) {
                        dialog.cancel();
                        textView.setText(response);
                    }
                });

        mQueue.addRequest(request);
    }

    /**
     * 发送MultipartRequest,可以传字符串参数、文件、Bitmap等参数,这种请求为POST类型
     */
    protected void sendMultiRequest() {
        // 2、创建请求
        MultipartRequest multipartRequest = new MultipartRequest(url,
                new RequestListener<String>() {
                    @Override
                    public void onComplete(int stCode, String response, String errMsg) {
                        // 该方法执行在UI线程
                    }
                });

        // 3、添加各种参数
        // 添加header
        multipartRequest.addHeader("header-name", "value");

        // 通过MultipartEntity来设置参数
        MultipartEntity multi = multipartRequest.getMultiPartEntity();
        // 文本参数
        multi.addStringPart("location", "模拟的地理位置");
        multi.addStringPart("type", "0");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 直接从上传Bitmap
        multi.addBinaryPart("images", bitmapToBytes(bitmap));
        // 上传文件
        multi.addFilePart("imgfile", new File("storage/emulated/0/test.jpg"));

        // 4、将请求添加到队列中
        mQueue.addRequest(multipartRequest);
    }

    private byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onDestroy() {
        mQueue.stop();
        super.onDestroy();
    }

}
