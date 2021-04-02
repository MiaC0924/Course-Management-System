package com.TeamProject.Person;

import java.time.LocalDate;

public class Student extends Person{
    private static int countID = 100000001;
    private int studentNumber;
    private String major;

    public Student(){
        super();
        studentNumber  = countID;
        major = "";
        ++countID;
    }

    public Student(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputMajor){
        super(inputName, inputGender, inputAddress, inputDOB);
        studentNumber  = countID;
        major = inputMajor;
        ++countID;
    }

    public void setMajor(String inputMajor){ major = inputMajor; }

    public int getStudentNumber(){ return studentNumber; }
    public String getMajor()     { return major;         }
}
