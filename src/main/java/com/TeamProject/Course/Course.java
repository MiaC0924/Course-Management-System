package com.TeamProject.Course;

import java.util.ArrayList;

public abstract class Course {
    private String name;
    private int code;
    Tutorial tutorial;
    PreCondition preCondition;



    public Course(int code) {
        this.code = code;
    }

    abstract void create();

    public String toStirng(){
        String r = name + code + "have "+tutorial+" and have ";
        return r;
    }

    //getters
    public String getCourseName(){ return name + code; }
    public int    getCode()      { return code;         }
    public String getMajor() { return name; }
    public PreCondition getPreCondition() { return preCondition; }

    //setter
    public void setMajor(String name) { this.name = name; }
    public void setPreCondition(PreCondition preCondition) { this.preCondition = preCondition; }

    public boolean match(Course inputCourse){
        if(name == inputCourse.getMajor() && code == inputCourse.getCode())
            return true;
        return false;
    }
}
