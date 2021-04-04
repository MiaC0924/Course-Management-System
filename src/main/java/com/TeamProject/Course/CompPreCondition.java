package com.TeamProject.Course;

import java.util.ArrayList;

public class CompPreCondition implements PreCondition{

    public String toString(){
        return "PreConditions :";
    }

    public ArrayList<Course> coursePre(int compcode){
        ArrayList<Course> pre = new ArrayList<>();
        CourseBuilding department = new Department();
        if(compcode == 3004) {
            Course comp1406 = department.orderTheCourse("COMP", 1406);
            pre.add(comp1406);
        }
        return pre;
    }
}
