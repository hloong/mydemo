package com.hloong.mydemo.activity;

import android.app.Activity;
import android.widget.Button;

import com.hloong.mydemo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_annotations)
public class AnnotationsActivity extends Activity {

    @Click(R.id.btn_AA_1)
    void onClickAA(){
        bt.setText("点击了我");
    }

    @ViewById(R.id.btn_AA_1)
    Button bt;


}
