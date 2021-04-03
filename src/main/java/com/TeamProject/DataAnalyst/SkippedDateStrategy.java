package com.TeamProject.DataAnalyst;

import java.util.ArrayList;
import java.util.List;

public class SkippedDateStrategy implements Strategy{
    List<Strategy> list = new ArrayList<>();

    public void addStrategy(Strategy s){
        list.add(s);
    }

    @Override
    public DataArray apply(DataArray dataArr) {
        for(Strategy s : list){
            dataArr = s.apply(dataArr);
        }
        return dataArr;
    }
}
