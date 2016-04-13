package com.hloong.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hloong.mydemo.widget.MyPointDialog;

public class BaseActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        showDialog();
    }

    private void showDialog(){
        final MyPointDialog dialog = new MyPointDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        },2000);
    }

    /**
     * 省掉findViewById
     * @param  布局id
     * @return findviewbyid
     */
    public <T extends View> T getView(int viewId) {
        return (T)this.findViewById(viewId);
    }
    
    public void openActivity (Class<?> activity) {
        startActivity(new Intent(this, activity));
    }
}
