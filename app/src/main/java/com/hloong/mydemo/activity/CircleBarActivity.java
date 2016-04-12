package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.widget.CircleBar;
import com.hloong.mydemo.widget.CircleBarTwoSider;

public class CircleBarActivity extends BaseActivity {
    private CircleBarTwoSider circleBar;
    private CircleBar circleBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_bar);
        
        circleBar = getView(R.id.circle);
        circleBar.setSweepAngle(120);
        circleBar.setText("27000");
        circleBar.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                circleBar.startCustomAnimation();
            }
        });
        
//        circleBar2 =  getView(R.id.circle2);
//        circleBar2.setSweepAngle(120);
//        circleBar2.setText("500");
//        circleBar2.setOnClickListener(new OnClickListener(){
//            @Override
//            public void onClick(View view){
//                circleBar2.startCustomAnimation();
//            }
//        });
        
    }
}
