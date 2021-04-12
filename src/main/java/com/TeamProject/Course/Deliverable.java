package com.TeamProject.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Deliverable {
    private Map<String,Float> grades;
    private boolean submit;
    private LocalDate deadline;
    private String name;

    public Deliverable(String name,LocalDate dl){
        this.name=name;
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
    public String getName(){ return name; }

    public void removeStu(String name){
        grades.remove(name);
    }

    public void addStu(String name){
        grades.put(name,0f);
    }
}
