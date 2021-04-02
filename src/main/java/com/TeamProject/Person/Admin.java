package com.TeamProject.Person;

import java.time.LocalDate;

public class Admin extends Person{
    private static int countID = 101;
    private int adminID;
    private boolean admitted;

    public Admin(){
        super();
        adminID  = countID;
        admitted = false;
        ++countID;
    }

    public Admin(String inputName, String inputGender, String inputAddress, LocalDate inputDOB){
        super(inputName, inputGender, inputAddress, inputDOB);
        adminID  = countID;
        admitted = false;
        ++countID;
    }

    public int getAdminID()    { return adminID;  }
}
