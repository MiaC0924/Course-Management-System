package com.TeamProject.Course;

public class Term {
    int    year;
    String season;

    public Term(int inputYear, String inputSeason){
        year   = inputYear;
        season = inputSeason;
    }

    @Override
    public String toString() {
        return season + year;
    }
}
