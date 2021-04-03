package com.TeamProject.DataAnalyst;

import java.util.ArrayList;
import java.util.List;

public class DataArray {
    String name;

    List<Data> dataArr;


    public DataArray(){
        name = "Default DataArray";
        dataArr = new ArrayList<>();
    }

    public DataArray(String name){
        this.name = name;
        dataArr = new ArrayList<>();
    }

    public Data get(int i){
        return dataArr.get(i);
    }

    public void set(int i, Data d){
        dataArr.set(i,d);
    }

    public int size(){
        return dataArr.size();
    }

    public void add(Data d){
        dataArr.add(d);
    }

    public void remove(Data d){
        dataArr.remove(d);
    }

    public int indexOf(Data d){
        return dataArr.indexOf(d);
    }

    public boolean noElement(){
        return dataArr.size() == 0;
    }

    public String toString(){
        return dataArr.toString();
    }
}
