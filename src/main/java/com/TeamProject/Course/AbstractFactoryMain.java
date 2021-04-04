package com.TeamProject.Course;


public class AbstractFactoryMain {
    public static void main(String[] args) {
        //create the department object
        CourseBuilding department = new Department();
        //Course object
        Course mathcourse = department.orderTheCourse("math");
        System.out.println(mathcourse.toStirng());


        Course compcourse = department.orderTheCourse("comp");
        System.out.println(compcourse.toStirng());
    }
}
