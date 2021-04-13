package com.TeamProject.DataAnalyst;

import java.util.Date;

public class StrategyCenter {
    Strategy sampleDateStrategy;
    Strategy sampleValueStrategy;
    Strategy skipNStrategy;
    Strategy dateDecorator;
    Strategy skippedDateStrategy;



    public  StrategyCenter(){
        sampleDateStrategy = new SampleDateStrategy();
        sampleValueStrategy = new SampleValueStrategy();
        skipNStrategy = new SkipNStrategy(2);
        dateDecorator = new DateDecorator(sampleDateStrategy , new Date() , new Date());
        skippedDateStrategy = new SkippedDateStrategy();
    }
    public DataArray useSampleDateStrategy(DataArray dataArray){
        return sampleDateStrategy.apply(dataArray);
    }
    public DataArray useSampleValueStrategy(DataArray dataArray){
        return sampleValueStrategy.apply(dataArray);
    }
    public DataArray useSkipNStrategy(DataArray dataArray){
        return skipNStrategy.apply(dataArray);
    }
    public DataArray useDateDecorator(DataArray dataArray){
        return dateDecorator.apply(dataArray);
    }
    public DataArray useSkippedDateStrategy(DataArray dataArray){
        return skippedDateStrategy.apply(dataArray);
    }
}
