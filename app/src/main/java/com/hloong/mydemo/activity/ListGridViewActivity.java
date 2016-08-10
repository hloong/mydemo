package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.quick.BaseViewHolder;
import com.hloong.mydemo.adapter.quick.QuickAdapter;
import com.hloong.mydemo.bean.ListBean;
import com.hloong.mydemo.bean.SizeBean;

import java.util.ArrayList;
import java.util.List;

public class ListGridViewActivity extends BaseActivity{

	private ListView mListView;
	private List<ListBean> mDatas = new ArrayList<ListBean>();
	private List<SizeBean> sizeBeans = new ArrayList<SizeBean>();
	private QuickAdapter<ListBean> mAdapter;
	private QuickAdapter<SizeBean> sizeAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		initDatas();

		mListView = getView(R.id.id_lv_main);
		
		mAdapter = new QuickAdapter<ListBean>(ListGridViewActivity.this, R.layout.item_lv_gv, mDatas){
			@Override
			protected void convert(final BaseViewHolder helper, final ListBean item){
				helper.setText(R.id.tvTitle, item.getTitle());
				//设置子布局中的adapter
				sizeAdapter = new QuickAdapter<SizeBean>(ListGridViewActivity.this,R.layout.item_gridview,item.getList()) {
                    @Override
                    protected void convert(BaseViewHolder helper, SizeBean item) {
                        // TODO Auto-generated method stub
                        helper.setText(R.id.ItemText, item.getName());
                        if (item.getNameIsSelect()) {
                            helper.setBackgroundRes(R.id.layout, R.drawable.btn_selected_red);
                         }else {
                            helper.setBackgroundRes(R.id.layout, R.drawable.btn_default_grey);
                         }
                    }
                };
				helper.setAdapter(R.id.my_gridview, sizeAdapter);
				
				
				helper.setOnItemClickListener(R.id.my_gridview, new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        SizeBean be = (SizeBean)item.getList().get(position);
                        be.setNameIsSelect(!be.getNameIsSelect());
                        for (int i = 0; i < item.getList().size(); i++) {
                            if (i != position) {
                                SizeBean beanlist = item.getList().get(i);
                                beanlist.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();
                        notifyDataSetChanged();
                    }
                });
			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// 设置适配器
		mListView.setAdapter(mAdapter);
	}

	private void initDatas(){
		ListBean bean = null;
		SizeBean sizeBean = null;
		
		
		sizeBean = new SizeBean("12", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("13", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("332", false);
		sizeBeans.add(sizeBean);

		bean = new ListBean("设置价格", sizeBeans);
		mDatas.add(bean);
		
		sizeBeans = new ArrayList<SizeBean>();
		sizeBean = new SizeBean("我的", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("你的", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("他的额", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("他们d的", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("他们d的", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("他d们的", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("他dd们的", false);
		sizeBeans.add(sizeBean);
		
		bean = new ListBean("设置人", sizeBeans);
		mDatas.add(bean);
		
		sizeBeans = new ArrayList<SizeBean>();
		sizeBean = new SizeBean("12", false);
		sizeBeans.add(sizeBean);
		sizeBean = new SizeBean("13", false);
		sizeBeans.add(sizeBean);
		bean = new ListBean("设置价格", sizeBeans);
		mDatas.add(bean);
		
		
	}

}
