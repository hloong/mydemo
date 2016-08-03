package com.hloong.mydemo.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.db.CommitUtils;
import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * greenDAO数据库 demo示例
 */
public class GreenDaoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = GreenDaoActivity.class.getSimpleName();
    private CommitUtils commitUtils;

    @BindView(R.id.et_staff_num)
    EditText mStaffNumInput;
    @BindView(R.id.et_staff_name)
    EditText mStaffNameInput;
    @BindView(R.id.et_staff_ege)
    EditText mStaffAgeInput;
    @BindView(R.id.et_staff_motto)
    EditText mStaffMottoInput;
    @BindView(R.id.btn_insert)
    Button mInsertButton;
    @BindView(R.id.btn_search)
    Button mSearchButton;
    @BindView(R.id.recyler_staffs)
    RecyclerView mStaffsRecylerView;


    private TextView mDialogCancle,mDialogDelete;
//    private StudentAdapter adapter;
    private Student student;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        commitUtils = new CommitUtils(this);
        
        mInsertButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
        mStaffsRecylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
//        case R.id.btn_insert:
//            insert();
//            break;
//        case R.id.btn_search:
//            query();
//            break;
//        case R.id.tv_dialog_cancle:
//        // 取消
//            dialog.dismiss();
//            break;
//        case R.id.tv_dialog_submit:
//        // 删除当前员工
//            break;
    }

    private void insert() {
    }
    private void query() {
    }


    //插入数据
    public void InsertData(View view) {

        Log.i(TAG, "insert Data");
        Student student = new Student();
        student.setAddress("北京");
        student.setAge(23);
        student.setId(10001l);
        student.setName("张三");
        commitUtils.insertStudent(student);
    }

    //插入多条数据
    public void InsertMulData(View view) {
        Log.i(TAG, "insert Mut Data");
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAddress("北京");
            student.setAge(23 + i);
//            student.setId(10001l + i);
            student.setName("张三");
            list.add(student);
        }
        commitUtils.insertMultStudent(list);
    }


    /**
     * //更改数据
     * @param view
     */
    public void updataData(View view) {
        Log.i(TAG, "data Data");
        Student student = new Student();
        student.setAge(1000);
        student.setName("xiaolei");
        student.setId(1l);
        student.setAddress("2432");
        commitUtils.updateStudent(student);
    }


    public void deleteData(View view) {
        Student student = new Student();
        student.setId(2l);
        commitUtils.deleteStudent(student);
    }

    public void oneList(View view) {
//        Student student = commitUtils.ListOneStudent(1);
        Student student = commitUtils.ListOneStudent(1);
        Log.i(TAG, student.getName() + "");

    }

    public void mutiList(View view) {
        List<Student> list = commitUtils.ListAll();
//        if(list!=null)
        Log.i(TAG, list.toString());
    }

    public  void QueryData(View view){
//       List<Student> query = commitUtils.Query();


//       for(int i=0;i<query.size();i++)
//       {
//          Log.i(TAG, query.get(i).getAge().toString()+" :"+ query.get(i).getId()) ;
//       }

        List<Student> students = commitUtils.Query();
        if(students!=null)
            for(int i=0;i<students.size();i++) {
                Log.i(TAG, students.get(i).getAge() + " :" + students.get(i).getId());
            }

    }


   
}
