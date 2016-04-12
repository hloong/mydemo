package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.BaseViewHolder;
import com.hloong.mydemo.adapter.QuickAdapter;
import com.hloong.mydemo.bean.SimpleListBean;

import java.util.ArrayList;
import java.util.List;

public class SimpleListActivity extends BaseActivity{

	private ListView mListView;
	private List<SimpleListBean> mDatas = new ArrayList<SimpleListBean>();
	private QuickAdapter<SimpleListBean> mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		initDatas();

		mListView = getView(R.id.id_lv_main);
		mAdapter = new QuickAdapter<SimpleListBean>(SimpleListActivity.this, R.layout.item_simple_list, mDatas){
			@Override
			protected void convert(BaseViewHolder helper, final SimpleListBean item){
				helper.setText(R.id.tv_title, item.getTitle());
				helper.setText(R.id.tv_describe, item.getDesc());
				helper.setText(R.id.tv_phone, item.getPhone());
				helper.setText(R.id.tv_time, item.getTime());
				helper.setOnClickListener(R.id.tv_phone, new OnClickListener() {
                    
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Toast.makeText(SimpleListActivity.this,item.getTitle() , Toast.LENGTH_LONG).show();
                    }
                });
			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// 设置适配器
		mListView.setAdapter(mAdapter);
	}

	private void initDatas(){
		SimpleListBean bean = null;
		bean = new SimpleListBean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new SimpleListBean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new SimpleListBean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new SimpleListBean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086","20130240122");
		mDatas.add(bean);
	}

}
