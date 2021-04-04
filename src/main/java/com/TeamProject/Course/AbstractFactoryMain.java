package com.TeamProject.Course;


public class AbstractFactoryMain {
    public static void main(String[] args) {
        //create the department object
        CourseBuilding department = new Department();
        //Course object
        Course mathcourse = department.orderTheCourse("MATH",2107);

        System.out.println(mathcourse.getPreCondition().coursePre(mathcourse.getCode()).get(0).getCourseName());


        //Course compcourse = department.orderTheCourse("comp",2000);
        //System.out.println(compcourse.toStirng());
    }
}
