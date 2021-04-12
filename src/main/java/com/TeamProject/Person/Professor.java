package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.MajorVisitor;
import com.TeamProject.Evaluator.OverallVisitor;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "Professor_table")
public class Professor extends Person implements Visitable{
    private static int countID = 9001;
    @Id
    private int profID;
    private String faculty;
    private ArrayList<Term> terms; //terms contain courseSectionIds
    private HashMap<String, ArrayList<Double>> passRates; //k-Term, v-list of passRate
    private double passRateOfCurr, passRateOverAll;

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
    public HashMap<String, ArrayList<Double>> getPassRates(){ return passRates; }

    //setters
    public void setFaculty(String inputFaculty){ faculty = inputFaculty; }
    public void setPassRateOfCurr(double passRate){ passRateOfCurr = passRate; }
    public void setPassRateOverall(double passRate){ passRateOverAll = passRate; }
    public void addTerm(Term t){ terms.add(t); }

    public void addPassRates(CourseSection c, Double rate){
        if(passRates.containsKey(c.getTerm())){
            passRates.get(c.getTerm()).add(rate);
        } else{
            ArrayList<Double> rateList = new ArrayList<>();
            rateList.add(rate);
            passRates.put(c.getTerm(), rateList);
        }
    }

    public void addSection(CourseSection section){
        for(Term t:terms){
            if(section.sameTerm(t)){
                for(int sectionID: t.getCourseSections()){
                    if(sectionID == section.getSectionID()){
                        return;
                    }
                }
                t.addCourseSections(section);
                return;
            }
        }

        Term added = new Term(section.getTermYear(), section.getTermSeason());
        added.addCourseSections(section);
        terms.add(added);
    }

    public void removeSection(CourseSection section){
        for(Term t: terms){
            if(section.sameTerm(t)){
                for(int sectionID: t.getCourseSections()){
                    if(sectionID == section.getSectionID()){
                        t.removeCourseSections(section);
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
