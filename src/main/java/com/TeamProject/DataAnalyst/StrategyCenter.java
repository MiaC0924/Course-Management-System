package com.TeamProject.DataAnalyst;

public class StrategyCenter {
    Strategy sampleDateStrategy;
    Strategy sampleValueStrategy;
    Strategy skipNStrategy;
    Strategy dateDecorator;
    Strategy skippedDateStrategy;
    DataArray dataArray;


    public void getDataArray(DataArray d){
        dataArray = d;
    }

    public void useStrategy(){

    }
}
