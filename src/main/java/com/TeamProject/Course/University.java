package com.TeamProject.Course;

import java.util.ArrayList;

public class University {
    public String name;
   // public ArrayList<CourseBuilding> departments;
    public CourseBuilding department;
    //default constructor
    public University(){
        name = "Carleton University";
    }
    //constructor
    public University(String name) {
        this.name = name;
        this.department = new Department();
       // departments = new ArrayList<CourseBuilding>();
    }
    //getter functions
    public String getName() { return name;}
    public CourseBuilding getDepartments() { return department;}
    //setter functions
    public void setName(String name) { this.name = name;}
    //public void setDepartments(ArrayList<CourseBuilding> departments) { this.departments = departments;}
}
