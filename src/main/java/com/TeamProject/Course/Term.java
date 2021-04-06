package com.TeamProject.Course;

import java.util.ArrayList;

public class Term {
    private int    year;
    private String season;
    private ArrayList<CourseSection> courses;

    public Term(int inputYear, String inputSeason){
        year   = inputYear;
        season = inputSeason;
        courses = new ArrayList<CourseSection>();
    }

    @Override
    public String toString() {
        return season + year;
    }

    public String getSeason(){ return season; }
    public int getYear(){ return year;}
    public ArrayList<CourseSection> getCourse(){ return courses; }

    public void add(CourseSection c){
        courses.add(c);
    }

    public void remove(CourseSection c){
        courses.remove(c);
    }

    public boolean sameTerm(Term t){
        if(this.getYear()==t.getYear()&&this.getSeason()==t.getSeason()){
            return true;
        }else{
            return false;
        }
    }
}
