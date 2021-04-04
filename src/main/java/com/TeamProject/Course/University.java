package com.TeamProject.Course;

import java.util.ArrayList;

public class University {
    public String name;
    public ArrayList<Department> departments;
    public Department department;
    //default constructor
    public University(){
        name = "Carleton University";
    }
    //constructor
    public University(String name) {
        this.name = name;
        department = new Department();
        departments = new ArrayList<Department>();
    }
    //getter functions
    public String getName() { return name;}
    public ArrayList<Department> getDepartments() { return departments;}
    //setter functions
    public void setName(String name) { this.name = name;}
    public void setDepartments(ArrayList<Department> departments) { this.departments = departments;}
}
