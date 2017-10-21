package com.example.apple.bizinabi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button findButton = (Button) view.findViewById(R.id.find_button);
        findButton.setOnClickListener(new View.OnClickListener() {
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
