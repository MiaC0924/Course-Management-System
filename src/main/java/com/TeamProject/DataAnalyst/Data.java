package com.TeamProject.DataAnalyst;

import com.TeamProject.Person.Person;
import com.TeamProject.Person.Student;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public abstract class Data {
    String action;
    Student stu;
    Date date;
    int size;
    Color color = Color.BLACK;
    public Data(Student stu,  Date d){
        this.stu = stu;
        this.date = d;
        size = 1;
    }

    public String getAction(){ return action; }

    public abstract String getValue();

    public int getSize(){
        return size;
    }

    public boolean equalDate(Data d) {
        return equalDate(d.date);
    }


    public boolean equalDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        int year1 = cal.get(Calendar.YEAR);
        int month1 = cal.get(Calendar.MONTH);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d);
        int year2 = cal1.get(Calendar.YEAR);
        int month2 = cal1.get(Calendar.MONTH);
        int day2 = cal1.get(Calendar.DAY_OF_MONTH);

        System.out.println("1-y/m/d: " + year1 + month1 + day1);
        System.out.println("2-y/m/d: " + year2 + month2 +day2);
        return year1 == year2 && month1 == month2 && day1 == day2;
    }


    public String toString(){
        return "Action: " + action + " Student : " + ((Person)stu).toString() + " Date: " + date + " Value :"+ getValue() + " [size : " + size + "]";
    }

    public boolean equalValue(Data d){
        return this.getValue().equals(d.getValue());
    }

    public void setColor(Color c){
        color = c;
    }
}
