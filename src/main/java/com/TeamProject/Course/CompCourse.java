package com.TeamProject.Course;

public class CompCourse extends Course{
    CourseFactory courseFactory;
    //int code;
    public CompCourse(CourseFactory courseFactory,int code){
        super(code);
        this.courseFactory = courseFactory;
        //this.code = code;
    }
    void create(){
        System.out.println("creating Course" + getCourseName());
        tutorial = courseFactory.addTut();
        preCondition = courseFactory.addPre();
    }
}
