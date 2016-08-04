package com.hloong.mydemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.Main2Activity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.net4demo.MultipartEntity;
import com.hloong.mydemo.net4demo.MultipartRequest;
import com.hloong.mydemo.net4demo.Request.RequestListener;
import com.hloong.mydemo.net4demo.RequestQueue;
import com.hloong.mydemo.net4demo.SimpleNet;
import com.hloong.mydemo.net4demo.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private String url = "http://op.juhe.cn/shanghai/police?key=4ceeff4e2486d39a40df48f3118e5a9c";
    @BindView(R.id.tv)      TextView textView;
    @BindView(R.id.lv_main) ListView lv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        lv_main.setAdapter(new DemoListAdapter());
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,demos[position].demoClass);
                startActivity(intent);
            }
        });

    }

    @OnClick(R.id.tv)
    void onClick(){
        sendStringRequest();
    }

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
        StringRequest request = new StringRequest(com.hloong.mydemo.net4demo.Request.HttpMethod.GET, "http://www.baidu.com",
                new RequestListener<String>() {
                    @Override
                    public void onComplete(int stCode, String response, String errMsg) {
                        dialog.cancel();
                        textView.setText(""+stCode);
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


    private static final Info[] demos = {
      new Info("ListView包含多种布局状态，聊天消息",ChatActivity.class),
      new Info("ListView包含多种布局状态",MultiListActivity.class),
      new Info("普通的ListView",SimpleListActivity.class),
      new Info("ListView下包含GridView",ListGridViewActivity.class),
      new Info("二维码扫描",ScaleActivity.class),
      new Info("带abcde列表展示",MultiListActivity.class),
      new Info("点击2边同时上升的环形demo",CircleBarActivity.class),
      new Info("股票，价格收益表格demo",GraphViewActivity.class),
      new Info("沉浸式界面",ChenJinShiActivity.class),
      new Info("缩放图片Maxtri测试",ScaleActivity.class),
      new Info("微信头像上传",PhotoActivity.class),
      new Info("生成temple样式的 滑动tap",Main2Activity.class),
      new Info("数据缓存ACache",AcacheActivity.class),
      new Info("rxjava与Retrofit示例",RxJavaRetrofitActivity.class),
      new Info("百度定位demo",BaiduLocActivity.class),
      new Info("EventBus事件总线demo",EventBusActivity.class),
    };

    /**
     * 列表标题
     */
    private static class Info{
        private final String title;
        private final Class<? extends Activity> demoClass;
        public Info(String title,Class<? extends Activity> demoClass){
            this.title = title;
            this.demoClass = demoClass;
        }
    }

    private class DemoListAdapter extends BaseAdapter{
        public DemoListAdapter(){
            super();
        }

        @Override
        public int getCount() {
            return demos.length;
        }

        @Override
        public Object getItem(int position) {
            return demos[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this,R.layout.item_simple_list,null);
            TextView title = (TextView)convertView.findViewById(R.id.tv_title);
            title.setText(demos[position].title);
            return convertView;
        }
    }

}
