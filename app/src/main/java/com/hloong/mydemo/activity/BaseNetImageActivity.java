package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hloong.mydemo.R;
import com.hloong.mydemo.image4base.ImageRequestManager;
import com.hloong.mydemo.net4base.IRequestCallback;
import com.hloong.mydemo.net4base.RequestFactory;



/**
 * 简单工厂模式下的图片加载框架，和网络加载框架示例
 */
public class BaseNetImageActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_net_image);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
        String img="https://img6.bdstatic.com/img/image/smallpic/weiju112.jpg";
        String url = "https://api.douban.com/v2/movie/top250";

        RequestFactory.getRequestManager().get(url, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("LONG","onSucess:"+response);
                tv.setText(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("LONG", "onFailure: " );
            }
        });

        ImageRequestManager.getRequest().display(this,image,img);
    }
}
