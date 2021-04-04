package com.TeamProject.Course;

public abstract class CourseBuilding {
    protected abstract Course makeCourse(String type);

    public Course orderTheCourse(String type){
        Course theCourse = makeCourse(type);

        theCourse.create();

        return theCourse;
    }
}
