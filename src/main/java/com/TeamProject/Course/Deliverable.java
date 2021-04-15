package com.TeamProject.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deliverable {
    private HashMap<String,Character> grades;
    private boolean submit;
    private LocalDate deadline;
    private String name;

    public Deliverable(String name,LocalDate deadline){
        this.name=name;
        this.deadline = deadline;
        grades = new HashMap<String,Character>();
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
        grades.put(name,' ');
    }

    public void setGrades(String name,Character grade){
        grades.replace(name,grade);
    }

    public boolean isSubmit() {
        return submit;
    }

    public String getDeadlineString() {
        return deadline.toString();
    }

    public String getName(){ return name; }
}
