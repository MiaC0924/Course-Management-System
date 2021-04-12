package com.TeamProject.Course;

import java.util.ArrayList;

public class Term {
    private int    year;
    private String season;
    private ArrayList<Integer> sectionIds;

    public Term(int year, String season){
        this.year   = year;
        this.season = season;
        sectionIds = new ArrayList<>();
    }

    @Override
    public String toString() {
        return season + year;
    }

    //getters
    public String getSeason(){ return season; }
    public int    getYear()  { return year;   }

    //courseSections
    public ArrayList<Integer> getCourseSections(){ return sectionIds; }
    public void addCourseSections(CourseSection c){ sectionIds.add(c.getSectionID()); }
    public void removeCourseSections(CourseSection c){
        sectionIds.remove(c.getSectionID());
    }

    public boolean sameTerm(Term t){
        return this.getYear() == t.getYear() && this.getSeason().equals(t.getSeason());
    }
}
