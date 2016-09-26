package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotFixActivity extends BaseActivity {
    private String hot=null;
    @BindView(R.id.btn_hotfix)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hot == null) {
                    int a = Integer.parseInt("2");//把“2”改成hot作为 未修复版本打包，然后在阿里百川hotfix修复，未签名的已经测试成功
                    hot = "hotfix修复";
                }
                Toast.makeText(HotFixActivity.this, hot,Toast.LENGTH_LONG).show();
            }
        });
    }

}
