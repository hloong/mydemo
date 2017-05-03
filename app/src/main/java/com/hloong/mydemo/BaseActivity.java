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

    public void setTitle(String title){
        toolbar_base.setTitle(title);
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


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            /**当UI隐藏时释放内存*/
            case TRIM_MEMORY_UI_HIDDEN:
                break;
            /**
             *表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，
             *系统可能会开始根据LRU缓存规则来去杀死进程了。
             */
            case TRIM_MEMORY_RUNNING_MODERATE:
                break;
            /**
             * 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，
             * 我们应该去释放掉一些不必要的资源以提升系统的性能，
             * 同时这也会直接影响到我们应用程序的性能。
             */
            case TRIM_MEMORY_RUNNING_LOW:
                break;
            /**
             * 表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。
             * 这个时候我们应当尽可能地去释放任何不必要的资源，
             * 不然的话系统可能会继续杀掉所有缓存中的进程，
             * 并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务。
             */
            case TRIM_MEMORY_RUNNING_CRITICAL:
                break;
            /**
             * 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，
             * 我们应该去释放掉一些不必要的资源以提升系统的性能，
             * 同时这也会直接影响到我们应用程序的性能。
             */
            case TRIM_MEMORY_BACKGROUND:
                break;
            /**
             * 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，
             * 如果手机内存还得不到进一步释放的话，那么我们的程序就有被系统杀掉的风险了。
             */
            case TRIM_MEMORY_MODERATE:
                break;
            /**
             * 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，
             * 系统会最优先考虑杀掉我们的应用程序，
             * 在这个时候应当尽可能地把一切可以释放的东西都进行释放。
             */
            case TRIM_MEMORY_COMPLETE:
                break;
        }
    }
}
