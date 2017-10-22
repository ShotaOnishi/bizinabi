package com.example.apple.bizinabi.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.apple.bizinabi.R;

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

        Button cuteFindButton = (Button) view.findViewById(R.id.cute_find_button);
        cuteFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location = data.getString("Location","nothing" );
                builder.setMessage(location);
                builder.show();
            }
        });

        Button beautyFindButton = (Button) view.findViewById(R.id.beauty_find_button);
        beautyFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location = data.getString("Location","nothing" );
                builder.setMessage(location);
                builder.show();

            }
        });

        Button kimonoFindButton = (Button) view.findViewById(R.id.kimono_find_button);
        kimonoFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location = data.getString("Location","nothing" );
                builder.setMessage(location);
                builder.show();

            }
        });

        return view;
    }
}
