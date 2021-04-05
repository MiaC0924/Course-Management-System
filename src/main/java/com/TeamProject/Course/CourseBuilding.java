package com.TeamProject.Course;

public abstract class CourseBuilding {
    protected abstract Course makeCourse(String type,int code);

    public Course orderTheCourse(String type,int code){
        Course theCourse = makeCourse(type,code);

        theCourse.create();

        return theCourse;
    }
}
