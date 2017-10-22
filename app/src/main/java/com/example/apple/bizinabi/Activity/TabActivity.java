package com.example.apple.bizinabi.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.apple.bizinabi.Adapter.TabLayoutPagerAdapter;
import com.example.apple.bizinabi.R;

/**
 * Created by apple on 2017/10/21.
 */

public class TabActivity extends AppCompatActivity implements FindFragment.OnPageChangeListener {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayoutPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        setViews();
    }

    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabLayoutPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onChange(int index) {
        //Fragmentから呼ばれる
        //ページのフラグメントを全て削除し再セット
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapter.destroyAllItem(viewPager);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);

    }
}
