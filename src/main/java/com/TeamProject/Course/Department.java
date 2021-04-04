package com.TeamProject.Course;

public class Department extends CourseBuilding{
    @Override
    protected Course makeCourse(String type) {
        Course theCourse = null;

        if(type.equals("math")){
            CourseFactory courseFactory = new MathFactory();
            theCourse = new MathCourse(courseFactory);
            theCourse.setName("math course");
        }
        else{
            if(type.equals("comp")){
                CourseFactory courseFactory = new CompFactory();
                theCourse = new CompCourse(courseFactory);
                theCourse.setName("comp course");
            }
        }
        return theCourse;
    }
}
