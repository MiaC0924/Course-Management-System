package com.TeamProject.Course;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Course_table")
public abstract class Course {
    private String name;
    private int code;
    Tutorial tutorial;
    PreCondition preCondition;

    public Course(int code) {
        this.code = code;
    }

    abstract void create();

    public String toString(){
        return name + code + "have "+tutorial+" and have "+preCondition;
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
        return name.equals(inputCourse.getMajor()) && code == inputCourse.getCode();
    }
}
