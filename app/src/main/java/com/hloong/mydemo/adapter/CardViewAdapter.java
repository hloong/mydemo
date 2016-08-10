package com.hloong.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hloong.mydemo.R;
import com.hloong.mydemo.bean.CardViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hl on 16/8/10.
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ItemCardViewHolder> {
    private List<CardViewBean> datas;
    private LayoutInflater inflater;
    private Context context;
    public CardViewAdapter(Context context){
        this.context = context;
        datas = getCardViewDatas();
        inflater = LayoutInflater.from(context);
    }
    @Override
    public ItemCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cardview,parent,false);
        return new ItemCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemCardViewHolder holder, int position) {
        holder.cd.setCardBackgroundColor(context.getResources().getColor(datas.get(position).getColor()));
        holder.tv.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ItemCardViewHolder extends RecyclerView.ViewHolder{
        private CardView cd;
        private TextView tv;
        public ItemCardViewHolder(View itemView) {
            super(itemView);
            cd = (CardView)itemView.findViewById(R.id.cd);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
    
    public static List<CardViewBean> getCardViewDatas(){
        List<CardViewBean> datas = new ArrayList<CardViewBean>();
        int[] colors = new int[]{
                R.color.color_card01,R.color.color_card02,R.color.color_card03,R.color.color_card04,R.color.color_card05
        };
        for (int i = 0; i <5 ; i++) {
            datas.add(new CardViewBean(colors[i],"CardView测试"+i));
        }
        return datas;
    }
    
    
}
