package com.hloong.mydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hloong.mydemo.R;
import com.hloong.mydemo.ui.DragLayout;
import com.hloong.mydemo.util.LogUtil;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class DragViewActivity extends Activity implements DragLayout.DragListener{
    @BindView(R.id.dl) DragLayout dl;
    @BindView(R.id.lv) ListView lv;
    @BindView(R.id.iv_icon) ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        ButterKnife.bind(this);
        lv.setAdapter(new ArrayAdapter<String>(DragViewActivity.this,android.R.layout.simple_list_item_1,
                new String[]{"item01","item02","item03","item04","item05","item06","item07"}
        ));

    }


    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onDrag(float percent) {
        ViewHelper.setScaleX(dl,1-percent);
        ViewHelper.setAlpha(iv,1-percent);
    }
    @OnClick(R.id.iv_icon)
    void onClick(){
        dl.open();
    }
    @OnItemClick(R.id.lv)
    void onItemClick(int postion){
        LogUtil.d("postion-->"+postion);
    }
}
