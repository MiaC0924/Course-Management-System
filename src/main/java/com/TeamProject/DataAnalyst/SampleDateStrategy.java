package com.TeamProject.DataAnalyst;

public class SampleDateStrategy implements Strategy {

    @Override
    public DataArray apply(DataArray dataArr) {
        DataArray newDataArr = new DataArray();
        newDataArr.name = dataArr.name;
        int flag = 0;
        for(int i = 0 ; i < dataArr.size(); i++){
            for(int j = 0; j < newDataArr.size(); j++){
                if(dataArr.get(i).equalDate(newDataArr.get(j))){
                    newDataArr.get(j).size++;
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                newDataArr.add(dataArr.get(i));
            }
            flag = 0;
        }
        return newDataArr;
    }
}
