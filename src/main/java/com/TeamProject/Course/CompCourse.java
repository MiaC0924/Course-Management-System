package com.TeamProject.Course;

public class CompCourse extends Course{
    CourseFactory courseFactory;
    public CompCourse(CourseFactory courseFactory,int code){
        super(code);
        this.courseFactory = courseFactory;
    }
    void create(){
        System.out.println("creating Course" + getMajor());
        tutorial = courseFactory.addTut();
        preCondition = courseFactory.addPre();
    }
}
