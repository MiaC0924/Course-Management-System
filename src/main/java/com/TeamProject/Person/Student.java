package com.TeamProject.Person;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO: The ArrayList finalGrades should be changed to an ArrayList of CourseSection,
// the list of final grade should be read from these sections
public class Student extends Person implements Visitable {
    private static int countID = 100000001;
    private int studentNumber;
    private String major;
    private Map<CourseSection,Character> finalGrades;
    private ArrayList<Term> terms;
    private double majorGPA, overallGPA;

    public Student(){
        super();
        studentNumber  = countID;
        major = "";
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        finalGrades = new HashMap<CourseSection,Character>();
        terms = new ArrayList<Term>();
    }

    public Student(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputMajor){
        super(inputName, inputGender, inputAddress, inputDOB);
        studentNumber  = countID;
        major = inputMajor;
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        terms = new ArrayList<Term>();
    }

    //setters
    public void setMajor(String inputMajor){ major = inputMajor; }
    public void setMajorGPA(double gpa){ majorGPA = gpa; }
    public void setOverallGPA(double gpa){ overallGPA = gpa; }
    public void addFinalGrade(CourseSection c,Character grade){ finalGrades.put(c,grade); }
    public void addCourse(CourseSection s){
        for(Term t:terms){
            if(t.sameTerm(s.getTerm())){
                for(CourseSection c:t.getCourse()){
                    if(c.getSectionName()==s.getSectionName()){
                        t.remove(c);
                        t.add(s);
                        return;
                    }
                }
                t.add(s);
                return;
            }
        }
        Term added = new Term(s.getTerm().getYear(),s.getTerm().getSeason());
        added.add(s);
        terms.add(added);
    }

    public void removeCourse(CourseSection s){
        for(Term t:terms){
            if(t.sameTerm(s.getTerm())){
                for(CourseSection c:t.getCourse()){
                    if(c.getSectionName()==s.getSectionName()){
                        t.remove(c);
                        return;
                    }
                }
            }
        }
    }

    //getters
    public int getStudentNumber(){ return studentNumber; }
    public String getMajor()     { return major;         }
    public double getMajorGPA()  { return majorGPA;      }
    public double getOverallGPA(){ return overallGPA;    }
    public ArrayList<Term> getTerms(){ return terms; }
    public Map<CourseSection,Character> getFinalGrades(){ return finalGrades; }

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
                for(CourseSection s:t.getCourse()){
                    if(c.equals(s)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
