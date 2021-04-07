package com.TeamProject.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Deliverable {
    private Map<String,Float> grades;
    private boolean submit;
    private LocalDate deadline;

    public Deliverable(LocalDate dl){
        deadline = dl;
        submit = true;
    }

    public void closeSubmission(){
        submit = false;
    }

    public void setDeadline(LocalDate ld){
        deadline = ld;
    }

    public void getGrade(String name){
        grades.get(name);
    }

    public void removeStu(String name){
        grades.remove(name);
    }

    public void addStu(String name){
        grades.put(name,0f);
    }
}
