package com.TeamProject.Course;

import java.util.ArrayList;

public class Course {
    private String major;
    private int code;
    private ArrayList<Course> preCondition;

    public Course(String inputMajor, int inputCode){
        major = inputMajor;
        code = inputCode;
        preCondition = new ArrayList<Course>();
    }

    //getters
    public String getCourseName(){ return major + code; }
    public int    getCode()      { return code;         }
    public String getMajor()     { return major;        }
    public ArrayList<Course> getPrecondition() { return preCondition; }

    //setters
    public void addPreCondition(Course c){ preCondition.add(c); }

    public boolean match(Course inputCourse){
        if(major == inputCourse.getMajor() && code == inputCourse.getCode())
            return true;
        return false;
    }
}
