package com.TeamProject.Course;

public class MathCourse extends Course{
    CourseFactory courseFactory;
    //private int code;

    public MathCourse(CourseFactory courseFactory,int code){
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
