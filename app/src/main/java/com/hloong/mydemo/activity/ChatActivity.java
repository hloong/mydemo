package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.BaseViewHolder;
import com.hloong.mydemo.adapter.MultiItemTypeInterface;
import com.hloong.mydemo.adapter.QuickAdapter;
import com.hloong.mydemo.bean.ChatMessage;

import java.util.ArrayList;

/**
 * @author hloong
 * @web http://www.hloong.com
 * 聊天消息界面
 */
public class ChatActivity extends BaseActivity{

	private ListView mListView;
	private ArrayList<ChatMessage> mDatas = new ArrayList<ChatMessage>();
	private QuickAdapter<ChatMessage> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		mListView = getView(R.id.id_lv_main);
		
		MultiItemTypeInterface<ChatMessage> multiItemTypeSupport = new MultiItemTypeInterface<ChatMessage>(){
			@Override
			public int getLayoutId(int position, ChatMessage msg){
				if (msg.isComMeg()){
					return R.layout.item_chat_from_msg;
				}
				return R.layout.item_chat_send_msg;
			}

			@Override
			public int getViewTypeCount(){
				return 2;
			}

			@Override
			public int getItemViewType(int postion, ChatMessage msg){
				if (msg.isComMeg()){
					return ChatMessage.RECIEVE_MSG;
				}
				return ChatMessage.SEND_MSG;
			}
		};

		initDatas();

		
		mAdapter = new QuickAdapter<ChatMessage>(ChatActivity.this, mDatas,multiItemTypeSupport){
			@Override
			protected void convert(BaseViewHolder helper, ChatMessage item){
				switch (helper.layoutId){
    				case R.layout.item_chat_from_msg:
    					helper.setText(R.id.chat_from_content, item.getContent());
    					helper.setText(R.id.chat_from_name, item.getName());
    					helper.setImageResource(R.id.chat_from_icon, item.getIcon());
    					break;
    				case R.layout.item_chat_send_msg:
    					helper.setText(R.id.chat_send_content, item.getContent());
    					helper.setText(R.id.chat_send_name, item.getName());
    					helper.setImageResource(R.id.chat_send_icon, item.getIcon());
    					break;
				}
			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// 设置适配器
		mListView.setDividerHeight(0);
		mListView.setAdapter(mAdapter);
	}

	private void initDatas(){
		ChatMessage msg = null;
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where are you2234 ",null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where are yo12222222222u ",null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where are 233333333you ",null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where a322222222re you ",null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where a232re you ",null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where323 are you ",null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where23sd are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where are you,hi asdffweqk ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where ar are you,hi asdffwesdddsddddddddqk are you,hi asdffweqk are you,hi asdffweqke you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where  are you,hi asdffweqk are you,hi asdffweqkare you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "wher are you,hi asdffweqk are you,hi asdffweqke are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where are you,hi asdffweqk are you,hi asdffweqk are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where are you ",
				null, false);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.renma, "renma", "where are you ",
				null, true);
		mDatas.add(msg);
		msg = new ChatMessage(R.mipmap.xiaohei, "xiaohei", "where  are you,hi asdffweqk are you,hi asdffweqkare you ",
				null, false);
		mDatas.add(msg);
	}

}
