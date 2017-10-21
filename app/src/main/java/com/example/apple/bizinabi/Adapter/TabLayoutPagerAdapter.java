package com.example.apple.bizinabi.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apple.bizinabi.Activity.FindFragment;
import com.example.apple.bizinabi.Activity.MapFragment;
import com.example.apple.bizinabi.Activity.RankingFragment;

/**
 * Created by apple on 2017/10/21.
 */

public class TabLayoutPagerAdapter extends FragmentPagerAdapter {
    public TabLayoutPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MapFragment.newInstance(android.R.color.transparent);
            case 1:
                return FindFragment.newInstance(android.R.color.transparent);
            case 2:
                return RankingFragment.newInstance(android.R.color.transparent);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bijinマップ";
            case 1:
                return "Bijin発見";
            case 2:
                return "Bijinランキング";
        }
        return null;
    }
}
