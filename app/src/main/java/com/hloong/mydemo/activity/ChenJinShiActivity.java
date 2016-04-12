package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;

/**
 * Created by hl on 16/2/26.
 */
public class ChenJinShiActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenjinshi);
        Toolbar toolbar = (Toolbar)findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
    }
}
