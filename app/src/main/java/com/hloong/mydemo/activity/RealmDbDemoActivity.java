package com.hloong.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.activity.realmdemo.AddStudentActivity;
import com.hloong.mydemo.activity.realmdemo.DeleteStudentActivity;
import com.hloong.mydemo.activity.realmdemo.QueryStudentActivity;
import com.hloong.mydemo.activity.realmdemo.UpdateStudentActivity;



public class RealmDbDemoActivity extends BaseActivity implements View.OnClickListener{
    private Button btnAddStudent,btnQueryStudent,btnDeleteStudent,btnUpdateStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_db_demo);
        setTitle("学生管理");
        initView();
    }

    private void initView() {
        btnAddStudent= (Button) findViewById(R.id.btnAddStudent);
        btnUpdateStudent= (Button) findViewById(R.id.btnUpdateStudent);
        btnQueryStudent= (Button) findViewById(R.id.btnQueryStudent);
        btnDeleteStudent= (Button) findViewById(R.id.btnDeleteStudent);
        btnDeleteStudent.setOnClickListener(this);
        btnQueryStudent.setOnClickListener(this);
        btnUpdateStudent.setOnClickListener(this);
        btnAddStudent.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnQueryStudent:
                intent=new Intent(RealmDbDemoActivity.this,QueryStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAddStudent:
                intent=new Intent(RealmDbDemoActivity.this,AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDeleteStudent:
                intent=new Intent(RealmDbDemoActivity.this,DeleteStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUpdateStudent:
                intent=new Intent(RealmDbDemoActivity.this,UpdateStudentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
