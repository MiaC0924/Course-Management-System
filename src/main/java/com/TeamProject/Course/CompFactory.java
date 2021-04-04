package com.TeamProject.Course;

public class CompFactory implements CourseFactory{
    public Tutorial addTut(){
        return new CompTutorial();
    }
    public PreCondition addPre(){
        return new CompPreCondition();
    }
}
