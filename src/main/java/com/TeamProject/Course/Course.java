package com.TeamProject.Course;

import java.util.ArrayList;

public abstract class Course {
    private String name;
    private int code;
    Tutorial tutorial;
    PreCondition preCondition;


    public void setName(String name) {
        this.name = name;
    }
    abstract void create();
    public String toStirng(){
        String r = name + "have "+tutorial+" and have "+preCondition;
        return r;
    }

    //getters
    public String getCourseName(){ return name + code; }
    public int    getCode()      { return code;         }
    public String getMajor() { return name; }


    public boolean match(Course inputCourse){
        if(name == inputCourse.getMajor() && code == inputCourse.getCode())
            return true;
        return false;
    }
}
