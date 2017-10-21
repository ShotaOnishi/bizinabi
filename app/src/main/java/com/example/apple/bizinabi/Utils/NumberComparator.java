package com.example.apple.bizinabi.Utils;

import android.hardware.Camera;

import com.example.apple.bizinabi.Model.Area;

import java.util.Comparator;

/**
 * Created by apple on 2017/10/22.
 */

public class NumberComparator implements Comparator<Area> {

    public int compare(Area a, Area b){
        long num_a = a.getNumber();
        long num_b = b.getNumber();

        if(num_a > num_b){
            return -1;
        }else if(num_a == num_b){
            return 0;
        }else {
            return 1;
        }
    }
}
