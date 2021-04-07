package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;

import java.time.LocalDate;
import java.util.ArrayList;

//TODO: The ArrayList passRateCol should be changed to an ArrayList of CourseSection
// the list of pass rate should be read from these sections
public class Professor extends Person implements Visitable{
    private static int countID = 9001;
    private int profID;
    private String faculty;
    private ArrayList<Double> passRateCol = new ArrayList<>();
    private ArrayList<Term> terms;
    private double passRateOfCurr, passRateOverAll;

    public Professor(){
        super();
        profID  = countID;
        faculty = "";
        passRateOfCurr = 0;
        passRateOverAll = 0;
        ++countID;
        course = new ArrayList<>();
    }

    public Professor(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputPW, String inputFaculty){
        super(inputName, inputGender, inputAddress, inputDOB, inputPW);
        profID  = countID;
        faculty = inputFaculty;
        passRateOfCurr = 0;
        passRateOverAll = 0;
        ++countID;
        course = new ArrayList<>();
    }

    //setters
    public void setFaculty(String inputFaculty){ faculty = inputFaculty; }
    public void setPassRateOfCurr(double passRate){ passRateOfCurr = passRate; }
    public void setPassRateOverAll(double passRate){ passRateOverAll = passRate; }
    public void addPassRates(double passRate){ passRateCol.add(passRate); }
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


    //getters
    public int getProfID()    { return profID;  }
    public String getFaculty(){ return faculty; }
    public double getPassRateOfCurr (){ return passRateOfCurr;  }
    public double getPassRateOverAll(){ return passRateOverAll; }
    public ArrayList<Term> getTerms(){ return terms; }
    public ArrayList<Double> getPassRateCol(){ return passRateCol; }

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
}
