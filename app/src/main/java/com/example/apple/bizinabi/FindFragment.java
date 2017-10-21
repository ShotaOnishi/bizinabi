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

public class FindFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public static FindFragment newInstance(@ColorRes int IdRes) {
        FindFragment frag = new FindFragment();
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
        View view = inflater.inflate(R.layout.fragment_find, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragmet_find);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));

        return view;
    }
}
