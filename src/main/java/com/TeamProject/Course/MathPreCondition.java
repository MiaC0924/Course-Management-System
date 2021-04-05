package com.TeamProject.Course;

import java.util.ArrayList;

public class MathPreCondition implements PreCondition{

    public String toString(){
        return "MathPreConditions :";
    }
    public ArrayList<Course> coursePre(int mathcode){
        ArrayList<Course> pre = new ArrayList<>();
        CourseBuilding department = new Department();
        if(mathcode == 2107) {
            Course math1007 = department.orderTheCourse("MATH", 1007);
            pre.add(math1007);
        }
        return pre;
    }
}
