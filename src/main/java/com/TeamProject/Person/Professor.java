package com.TeamProject.Person;

import java.time.LocalDate;

public class Professor extends Person{
    private static int countID = 9001;
    private int profID;
    private String faculty;

    public Professor(){
        super();
        profID  = countID;
        faculty = "";
        ++countID;
    }

    public Professor(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputFaculty){
        super(inputName, inputGender, inputAddress, inputDOB);
        profID  = countID;
        faculty = inputFaculty;
        ++countID;
    }

    public void setFaculty(String inputFaculty){ faculty = inputFaculty; }

    public int getProfID()    { return profID;  }
    public String getFaculty(){ return faculty; }

}
