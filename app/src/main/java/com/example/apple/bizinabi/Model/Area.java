package com.example.apple.bizinabi.Model;

/**
 * Created by apple on 2017/10/22.
 */

public class Area {
    long id;
    String name;
    long number;

    public Area(int id, String name, int number){
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public long getNumber(){
        return number;
    }

    public void setNumber(long number){
        this.number = number;
    }
}
