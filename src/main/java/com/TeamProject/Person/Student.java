package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.MajorVisitor;
import com.TeamProject.Evaluator.OverallVisitor;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person implements Visitable {
    private static int countID = 100000001;
    private int studentNumber;
    private String major;
    private ArrayList<Term> terms; //terms contain courseSections
    private HashMap<CourseSection, Character> finalGrades;
    private double majorGPA, overallGPA;
    private OverallVisitor overallVisitor;
    private MajorVisitor majorVisitor;

    public Student(){
        super();
        studentNumber = countID;
        major = "";
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        terms = new ArrayList<>();
        finalGrades = new HashMap<>();
    }

    public Student(String inputName, String inputGender, String inputEmail,
                   LocalDate inputDOB, String inputPW, String inputMajor){
        super(inputName, inputGender, inputEmail, inputDOB, inputPW);
        studentNumber = countID;
        major = inputMajor;
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        terms = new ArrayList<>();
        finalGrades = new HashMap<>();
    }

    //getters
    public int getStudentNumber(){ return studentNumber; }
    public String getMajor()     { return major;         }
    public double getMajorGPA()  { return majorGPA;      }
    public double getOverallGPA(){ return overallGPA;    }
    public ArrayList<Term> getTerms(){ return terms; }
    public HashMap<CourseSection,Character> getFinalGrades(){ return finalGrades; }

    //setters
    public void setMajor(String inputMajor){ major = inputMajor; }
    public void setMajorGPA(double gpa){ majorGPA = gpa; }
    public void setOverallGPA(double gpa){ overallGPA = gpa; }
    public void addFinalGrade(CourseSection c,Character grade){ finalGrades.put(c,grade); }
    public void addCourse(CourseSection s){
        for(Term t:terms){
            if(t.sameTerm(s.getTerm())){
                for(CourseSection c:t.getCourseSections()){
                    if(c.getSectionName().equals(s.getSectionName())){
                        t.removeCourseSections(c);
                        t.addCourseSections(s);
                        return;
                    }
                }
                t.addCourseSections(s);
                return;
            }
        }
        Term added = new Term(s.getTerm().getYear(),s.getTerm().getSeason());
        added.addCourseSections(s);
        terms.add(added);
    }

    public void removeCourse(CourseSection s){
        for(Term t:terms){
            if(t.sameTerm(s.getTerm())){
                for(CourseSection c:t.getCourseSections()){
                    if(c.getSectionName().equals(s.getSectionName())){
                        t.removeCourseSections(c);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


    @Override
    public void update(Subject s) {
        if(s instanceof CourseSection){
            addCourse((CourseSection) s);
        }
    }

    //Test
    public boolean containCourse(CourseSection c){
        for(Term t:terms){
            if(t.sameTerm(c.getTerm())){
                for(CourseSection s:t.getCourseSections()){
                    if(c.equals(s)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
