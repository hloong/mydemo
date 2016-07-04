package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.BaseViewHolder;
import com.hloong.mydemo.adapter.MultiItemTypeInterface;
import com.hloong.mydemo.adapter.QuickAdapter;
import com.hloong.mydemo.bean.Order;
import com.hloong.mydemo.bean.OrderLeft;
import com.hloong.mydemo.bean.OrderMiddle;
import com.hloong.mydemo.bean.OrderRight;

import java.util.ArrayList;

public class MultiListActivity extends BaseActivity{
    private ListView mListView;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private QuickAdapter<Order> mAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        mListView = getView(R.id.id_lv_main);
        MultiItemTypeInterface<Order>  multiItemTypeInterface = new MultiItemTypeInterface<Order>() {
            
            @Override
            public int getViewTypeCount() {
                // TODO Auto-generated method stub
                return 0;
            }
            
            @Override
            public int getLayoutId(int position, Order t) {
                // TODO Auto-generated method stub
                if (t.getType() == 1) {
                    return R.layout.item_view_flight;
                }else if (t.getType() == 2) {
                    return R.layout.item_view_ticket;
                }else {
                    return R.layout.item_view_middle;
                }
            }
            
            @Override
            public int getItemViewType(int postion, Order t) {
                // TODO Auto-generated method stub
                if (t.getType() == 1) {
                    return 1;
                }else if(t.getType() == 2){
                    return 2;
                }else {
                    return 3;
                }
            }
        };
        
        initDatas();
        mAdapter = new QuickAdapter<Order>(this,orders,multiItemTypeInterface) {
            
            @Override
            protected void convert(BaseViewHolder helper, Order item) {
                // TODO Auto-generated method stub
                switch (helper.layoutId) {
                case R.layout.item_view_flight:
                    helper.setText(R.id.tv_flight_airline, item.getOrderLeft().getAirline());
                    helper.setText(R.id.tv_flight_from, item.getOrderLeft().getFrom());
                    helper.setText(R.id.tv_flight_to, item.getOrderLeft().getTo());
                    break;
                case R.layout.item_view_middle:
                    helper.setText(R.id.tv_middle_title, item.getOrderMiddle().getTitle());
                    helper.setImageResource(R.id.iv_middle_image, item.getOrderMiddle().getImage());
                    break;
                case R.layout.item_view_ticket:
                    helper.setText(R.id.tv_ticke_expire_date, item.getOrderRight().getExpireDate());
                    helper.setText(R.id.tv_ticke_type, item.getOrderRight().getType());
                    helper.setText(R.id.tv_ticket_name, item.getOrderRight().getTitle());
                    helper.setText(R.id.tv_ticket_price, item.getOrderRight().getPrice());
                    break;
                default:
                    break;
                }
            }
        };
        mListView.setDividerHeight(0);
        mListView.setAdapter(mAdapter);
    }


    private void initDatas() {
        // TODO Auto-generated method stub
        Order order = null;
        OrderLeft orderLeft = null;
        OrderMiddle orderMiddle = null;
        OrderRight orderRight = null;
        
        
        orderLeft = new OrderLeft("灰机1", "北京", "深圳");
        order = new Order("ss", 20001, 1, orderLeft, null,null);
        orders.add(order);
        
        orderMiddle = new OrderMiddle(R.mipmap.ic_launcher, "图标1");
        order = new Order("ss", 20001, 3, null, orderMiddle,null);
        orders.add(order);
       
        orderRight = new OrderRight("特快票", "我擦", "2012-2015", "1111");
        order = new Order("ss", 20001, 2, null,null, orderRight);
        orders.add(order);
        //
        orderLeft = new OrderLeft("灰机2", "北京2", "深圳2");
        order = new Order("ss", 20001, 1, orderLeft, null,null);
        orders.add(order);
        
        orderMiddle = new OrderMiddle(R.mipmap.ic_launcher, "图标2");
        order = new Order("ss", 20001, 3,null , orderMiddle,null);
        orders.add(order);
        
        orderRight = new OrderRight("特快票2", "我擦2", "2012-2015", "2222");
        order = new Order("ss", 20001, 2, null,null, orderRight);
        orders.add(order);
        //
        orderLeft = new OrderLeft("灰机3", "北京3", "深圳3");
        order = new Order("ss", 20001, 1, orderLeft, null,null);
        orders.add(order);
        
        orderMiddle = new OrderMiddle(R.mipmap.ic_launcher, "图标3");
        order = new Order("ss", 20001, 3,null , orderMiddle,null);
        orders.add(order);
        
        orderRight = new OrderRight("特快票3", "我擦3", "2012-2015", "3333");
        order = new Order("ss", 20001, 2, null,null, orderRight);
        orders.add(order);
        
        
    }
}
