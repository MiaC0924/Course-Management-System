package com.TeamProject.DataAnalyst;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Date;

public class DateDecorator implements Strategy{
    private Strategy strategy;
    Date date;
    Date date2;
    int minOutRangeCounter =0;
    int maxOutRangeCounter = 0;
    //D>d = D after d. D<d = D before d.

    public DateDecorator(Strategy s,Date d, Date d2){
        strategy = s;
        date = d;
        date2 = d2;
    }

    @Override
    public DataArray apply(DataArray dataArr) {
        DataArray dataArr1 = strategy.apply(dataArr);
        //DataArray newDataArr = new DataArray();
        //newDataArr.name = dataArr1.name;

        for(int i = 0; i < dataArr1.size(); i++){
            if((dataArr1.get(i).date.compareTo(date)) < 0){
                dataArr.get(i).setColor(Color.blue);
                minOutRangeCounter++;
            }else if((dataArr1.get(i).date.compareTo(date2)) > 0){
                dataArr.get(i).setColor(Color.red);
                maxOutRangeCounter++;
            }else{
                dataArr.get(i).setColor(Color.black);
            }
        }
        return dataArr1;
    }

    public int getMinOutRangeCounter(){
        return minOutRangeCounter;
    }

    public  int getMaxOutRangeCounter(){
        return maxOutRangeCounter;
    }
}
