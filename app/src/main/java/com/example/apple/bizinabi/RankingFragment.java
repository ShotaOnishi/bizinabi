package com.example.apple.bizinabi;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        View view = inflater.inflate(R.layout.fragment_ranking, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragmet_ranking);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;
    }
}
