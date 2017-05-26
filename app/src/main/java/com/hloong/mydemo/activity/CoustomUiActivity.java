package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hloong.mydemo.R;

import java.util.ArrayList;
import java.util.List;

public class CoustomUiActivity extends AppCompatActivity {
    private ListView lv;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustom_ui);

        lv = (ListView) findViewById(R.id.lv);
        List data = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            data.add("item"+i);
        }
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
    }
}
