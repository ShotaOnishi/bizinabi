package com.example.apple.bizinabi.Activity;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.example.apple.bizinabi.Adapter.RankingFragmentAdapter;
import com.example.apple.bizinabi.Model.Area;
import com.example.apple.bizinabi.R;
import com.example.apple.bizinabi.Utils.NumberComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apple on 2017/10/21.
 */

public class RankingFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public static RankingFragment newInstance(@ColorRes int IdRes) {
        RankingFragment frag = new RankingFragment();
        Bundle b = new Bundle();
        b.putInt(BACKGROUND_COLOR, IdRes);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_ranking, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragmet_ranking);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));
        ListView listView = (ListView)view.findViewById(R.id.listView);

        ArrayList<Area> list = new ArrayList<>();
        RankingFragmentAdapter adapter = new RankingFragmentAdapter(getContext());

        list.add(new Area(1, "兼六園", 108));
        list.add(new Area(2, "金沢城公園", 37));
        list.add(new Area(3, "金沢駅", 64));
        list.add(new Area(4, "金沢中央郵便局", 75));
        list.add(new Area(5, "尾崎神社", 7));
        list.add(new Area(6, "尾山神社", 180));

        adapter.notifyDataSetChanged();
        Collections.sort(list, new NumberComparator());
        adapter.setAreaList(list);
        listView.setAdapter(adapter);

        return view;
    }
}
