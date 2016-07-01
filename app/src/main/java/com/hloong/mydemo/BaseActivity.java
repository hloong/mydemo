package com.hloong.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity {
    private FrameLayout baseContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        baseContent = (FrameLayout)findViewById(R.id.base_content);
    }
    /**
     * 省掉findViewById
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return (T)this.findViewById(viewId);
    }
    
    public void openActivity (Class<?> activity) {
        startActivity(new Intent(this, activity));
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, baseContent, true);
    }

}
