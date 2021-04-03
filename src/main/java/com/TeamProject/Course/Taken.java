package com.TeamProject.Course;

import com.TeamProject.Person.Student;

public class Taken {
    private CourseSection section;
    private Student student;
    private Character grade;

    public Taken(Student inputStudent, CourseSection inputSection){
        student = inputStudent;
        section = inputSection;
        grade   = null;
    }

    public void setGrade(Character inputGrade){ grade = inputGrade; }

    //getters
    public Student getStudent()     { return student; }
    public CourseSection getCourse(){ return section; }
    public Character getGrade()     { return grade;   }
}
