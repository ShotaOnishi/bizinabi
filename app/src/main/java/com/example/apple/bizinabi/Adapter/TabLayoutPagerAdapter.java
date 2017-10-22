package com.example.apple.bizinabi.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.example.apple.bizinabi.Activity.FindFragment;
import com.example.apple.bizinabi.Activity.MapFragment;
import com.example.apple.bizinabi.Activity.RankingFragment;

import static com.example.apple.bizinabi.Activity.MapFragment.newInstance;

/**
 * Created by apple on 2017/10/21.
 */

public class TabLayoutPagerAdapter extends FragmentPagerAdapter  {
    public TabLayoutPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", position);

        switch (position) {
            case 0:
                MapFragment map_fragment = new MapFragment();
                map_fragment.setArguments(bundle);
                return map_fragment;
            case 1:
                FindFragment find_fragment = new FindFragment();
                find_fragment.setArguments(bundle);
                return find_fragment;
            case 2:
                RankingFragment ranking_fragment = new RankingFragment();
                ranking_fragment.setArguments(bundle);
                return ranking_fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

//        if (position <= getCount()) {
//            FragmentManager manager = ((Fragment) object).getFragmentManager();
//            FragmentTransaction trans = manager.beginTransaction();
//            trans.remove((Fragment) object);
//            trans.commit();
//        }
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

    public void destroyAllItem(ViewPager pager) {
        for (int i = 0; i < getCount() - 1; i++) {
            try {
                Object obj = this.instantiateItem(pager, i);
                if (obj != null)
                    destroyItem(pager, i, obj);
            } catch (Exception e) {
            }
        }
    }
}
