package com.hloong.mydemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.hloong.mydemo.util.PhoneInfoUtil;

import java.lang.reflect.Field;

public class BaseActivity extends AppCompatActivity {
    private FrameLayout baseContent;
    private Toolbar toolbar_base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        baseContent = (FrameLayout)findViewById(R.id.base_content);
        toolbar_base = (Toolbar)findViewById(R.id.toolbar_base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //当系统版本大于4.4，为状态栏填充高度
            toolbar_base.setPadding(0, getStatusBarHeight(), 0, 0);
        }
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

    /**
     * 关闭标题栏
     */
    public void closeToolBar(){
        toolbar_base.setVisibility(View.GONE);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, baseContent, true);
    }

    /**
     * 设置沉浸式状态栏
     */
    protected void initStatusbar(Context context) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //当系统版本大于4.4，为状态栏填充高度
            toolbar_base.setPadding(0,getStatusBarHeight(),0,0);
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //5.0版本以及以上除去自带透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if (PhoneInfoUtil.getManufacturerName().equals("HUAWEI")){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }
    /**
     * 获取状态栏的高度
     * @return
     */
    protected int getStatusBarHeight(){
        try{
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
