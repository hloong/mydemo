package com.hloong.mydemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.hloong.mydemo.BaseActivity;
import com.hloong.mydemo.R;
import com.hloong.mydemo.fragment.EventTestFragment;
import com.hloong.mydemo.fragment.LimitSelectedFragment;
import com.hloong.mydemo.fragment.ListViewTestFragment;
import com.hloong.mydemo.fragment.ScrollViewTestFragment;
import com.hloong.mydemo.fragment.SimpleFragment;
import com.hloong.mydemo.fragment.SingleChooseFragment;

/**
 * Created by hl on 16/8/22.
 */
public class TabFlowActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTabTitles = new String[]
            {"Muli Selected", "Limit 3",
                    "Event Test", "ScrollView Test", "Single Choose", "Gravity", "ListView Sample"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_flow);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new SimpleFragment();
                    case 1:
                        return new LimitSelectedFragment();
                    case 2:
                        return new EventTestFragment();
                    case 3:
                        return new ScrollViewTestFragment();
                    case 4:
                        return new SingleChooseFragment();
                    case 5:
                        return new ListViewTestFragment();
                    default:
                        return new EventTestFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return mTabTitles[position];
            }

            @Override
            public int getCount() {
                return mTabTitles.length;
            }
        });

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mTabLayout.setupWithViewPager(mViewPager);
    }

}
