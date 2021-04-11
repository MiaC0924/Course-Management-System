package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.MajorVisitor;
import com.TeamProject.Evaluator.OverallVisitor;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "Professor_table")
public class Professor extends Person implements Visitable{
    private static int countID = 9001;
    private int profID;
    private String faculty;
    private ArrayList<Term> terms; //terms contain courseSections
    private HashMap<CourseSection, Double> passRates;
    private double passRateOfCurr, passRateOverAll;
    private OverallVisitor overallVisitor;
    private MajorVisitor majorVisitor;

    public Professor(){
        super();
        profID  = countID;
        faculty = "";
        passRateOfCurr = 0;
        passRateOverAll = 0;
        ++countID;
        terms = new ArrayList<>();
        passRates = new HashMap<>();
    }

    public Professor(String inputName, String inputGender, String inputEmail,
                     LocalDate inputDOB, String inputPW, String inputFaculty){
        super(inputName, inputGender, inputEmail, inputDOB, inputPW);
        profID  = countID;
        faculty = inputFaculty;
        passRateOfCurr  = 0;
        passRateOverAll = 0;
        ++countID;
        terms = new ArrayList<>();
        passRates = new HashMap<>();
    }

    //getters
    public int getProfID()    { return profID;  }
    public String getFaculty(){ return faculty; }
    public ArrayList<Term> getTerms(){ return terms; }
    public HashMap<CourseSection, Double> getPassRates(){ return passRates; }

    //setters
    public void setFaculty(String inputFaculty){ faculty = inputFaculty; }
    public void setPassRateOfCurr(double passRate){ passRateOfCurr = passRate; }
    public void setPassRateOverall(double passRate){ passRateOverAll = passRate; }
    public void addPassRates(CourseSection c, Double rate){ passRates.put(c, rate); }
    public void addTerm(Term t){ terms.add(t); }

    public void addSection(CourseSection s){
        for(Term t:terms){
            if(s.sameTerm(t)){
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
        Term added = new Term(s.getTermYear(),s.getTermSeason());
        added.addCourseSections(s);
        terms.add(added);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public double getPassRateOfCurr (){
        OverallVisitor calculator = new OverallVisitor();
        return this.accept(calculator);
    }

    public double getPassRateOverAll(){
        MajorVisitor calculator = new MajorVisitor();
        return this.accept(calculator);
    }

    @Override
    public void update(Subject s) {
        if(s instanceof CourseSection){
            addSection((CourseSection) s);
        }
    }
}
