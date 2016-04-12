package com.hloong.mydemo.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.graphview.CustomLabelFormatter;
import com.hloong.mydemo.graphview.GraphView;
import com.hloong.mydemo.graphview.GraphView.GraphViewData;
import com.hloong.mydemo.graphview.GraphViewSeries;
import com.hloong.mydemo.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.hloong.mydemo.graphview.LineGraphView;
import com.hloong.mydemo.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GraphViewActivity extends BaseActivity {
    private GraphView graphView;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);
        initGraphView();
    }
    
    private void initGraphView() {
        // TODO Auto-generated method stub
        String str1 = "12", str2 = "12", str3 = "12", str4 = "12",
                str5 = "12", str6 = "12", str7 = "12";
        long now = new Date().getTime();
        long t = 86400000;

        GraphViewSeries exampleSeries =
            new GraphViewSeries("", new GraphViewSeriesStyle(Color.rgb(234, 81, 51), 4),
                new GraphViewData[] {
                new GraphViewData(now - 6 * t, Double.parseDouble(str1)),
                new GraphViewData(now - 5 * t, Double.parseDouble(str2)),
                new GraphViewData(now - 4 * t, Double.parseDouble(str3)),
                new GraphViewData(now - 3 * t, Double.parseDouble(str4)),
                new GraphViewData(now - 2 * t, Double.parseDouble(str5)),
                new GraphViewData(now - 1 * t, Double.parseDouble(str6)),
                new GraphViewData(now, Double.parseDouble(str7)) });

        GraphView graphView;
        graphView = new LineGraphView(this, "");
        ((LineGraphView) graphView).setBackgroundColor(Color
                .parseColor("#20ea5133"));// 选择的背景颜色(淡蓝色)
        ((LineGraphView) graphView).setDataPointsRadius(0);
        ((LineGraphView) graphView).setDrawBackground(true);
        // 线条色


        /** 字体色 */
        int fontColor = Color.parseColor("#808080");
        // 风格色//表格线颜色
        graphView.getGraphViewStyle().setGridColor(Color.parseColor("#808080"));
        graphView.getGraphViewStyle().setHorizontalLabelsColor(fontColor);
        graphView.getGraphViewStyle().setVerticalLabelsColor(fontColor);
        // x轴标签数
        graphView.getGraphViewStyle().setNumHorizontalLabels(7);
        // y轴标签数
        graphView.getGraphViewStyle().setNumVerticalLabels(7);
     // 隐藏y轴标签
        graphView.setShowVerticalLabels(false);  
        // 字号
        graphView.getGraphViewStyle().setTextSize(Utils.sp2px(this, 8));
        // 图标利率数值字号    
        graphView.getGraphViewStyle().setTextSizeDot(Utils.sp2px(this, 15));
        graphView.getGraphViewStyle().setVerticalLabelsAlign(Align.RIGHT);
        graphView.getGraphViewStyle().setVerticalLabelsWidth(Utils.sp2px(this, 37));
        graphView.addSeries(exampleSeries);
 
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd", Locale.CHINESE);
        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
          @Override
          public String formatLabel(double value, boolean isValueX) {
            if (isValueX) {
              Date d = new Date((long) value);
              return dateFormat.format(d);
            }
            return null;
          }
          
        });
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph);
        layout.addView(graphView);
    }
}
