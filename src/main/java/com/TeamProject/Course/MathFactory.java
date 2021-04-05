package com.TeamProject.Course;

public class MathFactory implements CourseFactory{
    public Tutorial addTut(){
        return new MathTutorial();
    }
    public PreCondition addPre(){
        return new MathPreCondition();
    }
}
