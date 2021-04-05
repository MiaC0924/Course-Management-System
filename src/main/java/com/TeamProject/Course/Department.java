package com.TeamProject.Course;

public class Department extends CourseBuilding{
    @Override
    protected Course makeCourse(String type,int code) {
        Course theCourse = null;

        if(type.equals("MATH")){
            CourseFactory courseFactory = new MathFactory();
            theCourse = new MathCourse(courseFactory,code);
            theCourse.setMajor("MATH");
        }
        else{
            if(type.equals("COMP")){
                CourseFactory courseFactory = new CompFactory();
                theCourse = new CompCourse(courseFactory,code);
                theCourse.setMajor("COMP");
            }
        }
        return theCourse;
    }
}
