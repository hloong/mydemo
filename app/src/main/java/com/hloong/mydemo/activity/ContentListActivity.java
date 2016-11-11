package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.adapter.ContentListAdapter;
import com.hloong.mydemo.bean.User;
import com.hloong.mydemo.ui.ContentListView;
import com.hloong.mydemo.util.ChineseToPinyinHelper;
import com.hloong.mydemo.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContentListActivity extends BaseActivity {
    private List<User> list;
    private ContentListAdapter adapter;
    private ListView listView;
    private TextView textView;
    private ContentListView contentListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);

        listView = (ListView)findViewById(R.id.lv);
        initData();
        adapter = new ContentListAdapter(this,list);
        listView.setAdapter(adapter);
        textView = (TextView)findViewById(R.id.show_letter_in_center);
        contentListView = (ContentListView)findViewById(R.id.letter_index_view);
        contentListView.setTextViewDialog(textView);
        contentListView.setUpdateListView(new ContentListView.UpdateListView() {
            @Override
            public void updateListView(String currentChar) {
                int positionForSection = adapter.getPositionForSection(currentChar.charAt(0));
                listView.setSelection(positionForSection);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int sectionForPostion = adapter.getSectionForPosition(firstVisibleItem);
                contentListView.updateContentListView(sectionForPostion);
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        String[] allUserNames = getResources().getStringArray(R.array.arrUsernames);
        LogUtil.d("initdata-->"+allUserNames.toString());
        for (String allUserName : allUserNames) {
            User user = new User();
            user.setUserName(allUserName);
            String convert = ChineseToPinyinHelper.getInstance().getPinyin(allUserName).toUpperCase();
            user.setPinyin(convert);
            String substring = convert.substring(0, 1);
            if (substring.matches("[A-Z]")) {
                user.setFirstLetter(substring);
            }else{
                user.setFirstLetter("#");
            }
            list.add(user);
        }
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                if (lhs.getFirstLetter().contains("#")) {
                    return 1;
                } else if (rhs.getFirstLetter().contains("#")) {
                    return -1;
                }else{
                    return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
                }
            }
        });
    }


}
