package com.example.apple.bizinabi.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.apple.bizinabi.R;
import com.google.android.gms.maps.model.LatLng;
import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.List;

/**
 * Created by apple on 2017/10/21.
 */

public class FindFragment extends Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public interface OnPageChangeListener {
        void onChange(int index);
    }


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

        //可愛い(0)
        Button cuteFindButton = (Button) view.findViewById(R.id.cute_find_button);
        cuteFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location_latitude = data.getString("Location_latitude", "nothing");
                String location_longitude = data.getString("Location_longitude", "nothing");
                double latitude = Double.valueOf(location_latitude);
                double longitude = Double.valueOf(location_longitude);
                Location location = new Location("location");
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                saveLocation(location, 0);
                builder.setMessage("可愛いスポット登録完了！");
                builder.show();
                refresh();
            }
        });

        //大人（1）
        Button beautyFindButton = (Button) view.findViewById(R.id.beauty_find_button);
        beautyFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location_latitude = data.getString("Location_latitude", "nothing");
                String location_longitude = data.getString("Location_longitude", "nothing");
                double latitude = Double.valueOf(location_latitude);
                double longitude = Double.valueOf(location_longitude);
                Location location = new Location("location");
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                saveLocation(location, 1);
                builder.setMessage("大人スポット登録完了！");
                builder.show();
                refresh();


            }
        });

        //着物（2）
        Button kimonoFindButton = (Button) view.findViewById(R.id.kimono_find_button);
        kimonoFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                String location_latitude = data.getString("Location_latitude", "nothing");
                String location_longitude = data.getString("Location_longitude", "nothing");
                double latitude = Double.valueOf(location_latitude);
                double longitude = Double.valueOf(location_longitude);
                Location location = new Location("location");
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                saveLocation(location, 2);
                builder.setMessage("着物スポット登録完了！");
                builder.show();
                refresh();

            }
        });

        return view;
    }


    public void saveLocation(Location location, final int type) {
        //位置情報の登録
        //Location geo = new Location("location");

        NCMBObject obj = new NCMBObject("Spot");
        obj.put("geo", location);
        // kawaii:0, otona:1, kimono:2
        obj.put("type", type);
        // データストアへの登録
        obj.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {
                    //保存に失敗した場合の処理
                    Log.d("DATA_SAVE", "失敗" + String.valueOf(type) + "番です");
                } else {
                    //保存に成功した場合の処理
                    Log.d("DATA_SAVE", "成功" + String.valueOf(type) + "番です");
                }
            }
        });
    }

    public void refresh(){

        Bundle bundle = getArguments();
        int index = bundle.getInt("INDEX");
        Activity activity = getActivity();
        if(activity instanceof OnPageChangeListener == false){
            return;
        }
        ((OnPageChangeListener)activity).onChange(index);
    }

}

