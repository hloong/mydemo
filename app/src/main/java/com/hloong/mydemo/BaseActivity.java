package com.hloong.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BaseActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

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
}
