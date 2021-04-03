package com.TeamProject.Person;

import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import java.time.LocalDate;
import java.util.ArrayList;

//TODO: The ArrayList passRateCol should be changed to an ArrayList of CourseSection
// the list of pass rate should be read from these sections
public class Professor extends Person implements Visitable {
    private static int countID = 9001;
    private int profID;
    private String faculty;
    private ArrayList<Double> passRateCol = new ArrayList<>();
    private double passRateOfCurr, passRateOverAll;

    public Professor(){
        super();
        profID  = countID;
        faculty = "";
        passRateOfCurr = 0;
        passRateOverAll = 0;
        ++countID;
    }

    public Professor(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputFaculty){
        super(inputName, inputGender, inputAddress, inputDOB);
        profID  = countID;
        faculty = inputFaculty;
        passRateOfCurr = 0;
        passRateOverAll = 0;
        ++countID;
    }

    //setters
    public void setFaculty(String inputFaculty){ faculty = inputFaculty; }
    public void setPassRateOfCurr(double passRate){ passRateOfCurr = passRate; }
    public void setPassRateOverAll(double passRate){ passRateOverAll = passRate; }
    public void addPassRates(double passRate){ passRateCol.add(passRate); }

    //getters
    public int getProfID()    { return profID;  }
    public String getFaculty(){ return faculty; }
    public double getPassRateOfCurr (){ return passRateOfCurr;  }
    public double getPassRateOverAll(){ return passRateOverAll; }
    public ArrayList<Double> getPassRateCol(){ return passRateCol; }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
