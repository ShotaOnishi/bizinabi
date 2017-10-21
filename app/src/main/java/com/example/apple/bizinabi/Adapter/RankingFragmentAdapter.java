package com.example.apple.bizinabi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apple.bizinabi.Model.Area;
import com.example.apple.bizinabi.R;

import java.util.ArrayList;

/**
 * Created by apple on 2017/10/22.
 */

public class RankingFragmentAdapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Area> areaList;

    public RankingFragmentAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setAreaList(ArrayList<Area> areaList) {
        this.areaList = areaList;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return areaList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.area_ranking_row,parent,false);

        ((TextView)convertView.findViewById(R.id.rank)).setText(String.valueOf(position+1) + "位");
        ((TextView)convertView.findViewById(R.id.name)).setText(areaList.get(position).getName());
        ((TextView)convertView.findViewById(R.id.num)).setText(String.valueOf(areaList.get(position).getNumber()));

        return convertView;
    }
}
