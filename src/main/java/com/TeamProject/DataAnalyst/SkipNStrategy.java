package com.TeamProject.DataAnalyst;

public class SkipNStrategy implements Strategy {
    int skipN;
    public  SkipNStrategy(int n){
        skipN = n;


    }
    @Override
    public DataArray apply(DataArray dataArr) {
        DataArray newDataArr = new DataArray();
        newDataArr.name = dataArr.name;

        for(int i = 0 ; i < dataArr.size(); i+=skipN){
            newDataArr.add(dataArr.get(i));
        }



        return newDataArr;
    }
}
