package com.TeamProject.Course;

public class MathCourse extends Course{
    CourseFactory courseFactory;
    public MathCourse(CourseFactory courseFactory){
        this.courseFactory = courseFactory;
    }
    void create(){
        System.out.println("creating Course" + getMajor());
        tutorial = courseFactory.addTut();
        preCondition = courseFactory.addPre();
    }
}
