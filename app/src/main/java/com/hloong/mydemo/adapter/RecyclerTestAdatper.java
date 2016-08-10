package com.hloong.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hloong.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hl on 16/8/5.
 */
public class RecyclerTestAdatper extends RecyclerView.Adapter<RecyclerTestAdatper.ViewHolder> {
    private LayoutInflater inflater;
    private List<String> data = new ArrayList<String>();

    public RecyclerTestAdatper(Context context){
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < 10; i++) {
            int index = i+1;
            data.add("item 原生"+index);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_simple_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }

    public void addItem(List<String> newdata){
        if (null != newdata && data.size() != 0 ){
            data.clear();
            data.addAll(newdata);
            notifyDataSetChanged();
        }
    }
    public void addMoreItem(List<String> moredata){
        if (null != moredata && moredata.size() > 0){
            data.addAll(moredata);
            notifyDataSetChanged();
        }
    }


}
