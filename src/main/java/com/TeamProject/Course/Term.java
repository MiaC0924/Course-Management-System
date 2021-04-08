package com.TeamProject.Course;

import java.util.ArrayList;

public class Term {
    private int    year;
    private String season;
    private ArrayList<CourseSection> sections;

    public Term(int inputYear, String inputSeason){
        year   = inputYear;
        season = inputSeason;
        sections = new ArrayList<>();
    }

    @Override
    public String toString() {
        return season + year;
    }

    //getters
    public String getSeason(){ return season; }
    public int    getYear()  { return year;   }

    //courseSections
    public ArrayList<CourseSection> getCourseSections(){ return sections; }
    public void addCourseSections(CourseSection c){ sections.add(c); }
    public void removeCourseSections(CourseSection c){
        sections.remove(c);
    }

    public boolean sameTerm(Term t){
        return this.getYear() == t.getYear() && this.getSeason().equals(t.getSeason());
    }
}
