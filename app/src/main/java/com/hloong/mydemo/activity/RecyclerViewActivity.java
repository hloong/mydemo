package com.hloong.mydemo.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.CardViewAdapter;
import com.hloong.mydemo.adapter.RecyclerTestAdatper;
import com.hloong.mydemo.ui.recycleview.DividerItemDecoration;
import com.hloong.mydemo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recylerview)  RecyclerView rv;
    @BindView(R.id.btn_vertical) Button bt_vertical;
    @BindView(R.id.btn_h)        Button btn_h;
    @BindView(R.id.btn_grid)     Button btn_grid;
    @BindView(R.id.srl)          SwipeRefreshLayout swipeRefreshLayout;
    RecyclerTestAdatper adatper;
    CardViewAdapter cdadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        adatper = new RecyclerTestAdatper(this);
        cdadapter = new CardViewAdapter(this);
        initView(1);
        initRefresh();
    }

    private void initRefresh() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false,0,(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> data = new ArrayList<String>();
                        for (int i = 0; i <10 ; i++) {
                            data.add("下拉刷新替换"+i);
                        }
                        adatper.addItem(data);
                        swipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("更新了10条数据");
                    }
                },1000);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initView(int i) {
        rv.removeAllViews();
        rv.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        if (i == 2){
            llm.setOrientation(OrientationHelper.HORIZONTAL);
            rv.setLayoutManager(llm);
            rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        }else if (i == 3){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            rv.setLayoutManager(gridLayoutManager);
        }else {
            llm.setOrientation(OrientationHelper.VERTICAL);
            rv.setLayoutManager(llm);
            rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        }
        rv.setAdapter(adatper);
//        rv.setAdapter(cdadapter);//cardview

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && llm.findLastVisibleItemPosition() + 1 == adatper.getItemCount()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> data = new ArrayList<String>();
                            for (int i = 0; i < 10 ; i++) {
                                data.add("last---"+i);
                            }
                            adatper.addMoreItem(data);
                        }
                    },5000);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @OnClick({R.id.btn_vertical,R.id.btn_h,R.id.btn_grid})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_vertical:
                initView(1);
                break;
            case R.id.btn_h:
                initView(2);
                break;
            case R.id.btn_grid:
                initView(3);
                break;
        }

    }

}
