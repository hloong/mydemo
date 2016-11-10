package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.SizeBean;
import com.hloong.mydemo.util.ACache;

/**
 * Acache存储数据示例
 */
public class AcacheActivity extends BaseActivity {
    private ACache aCache;
    private SizeBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acache);

        aCache = ACache.get(this);
        final EditText editText = getView(R.id.et_text);
        getView(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText().toString())){
                    aCache.put("Text",editText.getText().toString());
                }
                runThread();
            }
        });
        getView(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(aCache.getAsString("Text"));
            }
        });


    }

    private void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AcacheActivity.this,"更新主线程UI",Toast.LENGTH_LONG).show();
            }
        });

    }

}
