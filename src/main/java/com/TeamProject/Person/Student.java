package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;

import java.time.LocalDate;
import java.util.ArrayList;

//TODO: The ArrayList finalGrades should be changed to an ArrayList of CourseSection,
// the list of final grade should be read from these sections
public class Student extends Person implements Visitable {
    private static int countID = 100000001;
    private int studentNumber;
    private String major;
    private ArrayList<Character> finalGrades = new ArrayList<>();
    private ArrayList<CourseSection> courses;
    private double majorGPA, overallGPA;

    public Student(){
        super();
        studentNumber  = countID;
        major = "";
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        courses = new ArrayList<CourseSection>();
    }

    public Student(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputMajor){
        super(inputName, inputGender, inputAddress, inputDOB);
        studentNumber  = countID;
        major = inputMajor;
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
    }

    //setters
    public void setMajor(String inputMajor){ major = inputMajor; }
    public void setMajorGPA(double gpa){ majorGPA = gpa; }
    public void setOverallGPA(double gpa){ overallGPA = gpa; }
    public void addFinalGrade(Character grade){ finalGrades.add(grade); }

    //getters
    public int getStudentNumber(){ return studentNumber; }
    public String getMajor()     { return major;         }
    public double getMajorGPA()  { return majorGPA;      }
    public double getOverallGPA(){ return overallGPA;    }
    public ArrayList<Character> getFinalGrades(){ return finalGrades; }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


    @Override
    public void update(Subject s) {
        if(s instanceof CourseSection){
            for(CourseSection c:courses){
                if(c.getSectionID()==((CourseSection) s).getSectionID()){
                    courses.remove(c);
                    courses.add((CourseSection) s);
                    return;
                }
            }
            courses.add((CourseSection) s);
        }
    }
}
