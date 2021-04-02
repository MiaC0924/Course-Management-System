package com.TeamProject.Person;

import java.time.LocalDate;

public abstract class Person {
    protected String name, gender, address;
    protected LocalDate birthDay;

    public Person(){
        name = "";
        gender = "";
        address = "";
        birthDay = LocalDate.parse("9999-01-01");
    }

    public Person(String inputName, String inputGender, String inputAddress, LocalDate inputDOB){
        name     = inputName;
        gender   = inputGender;
        address  = inputAddress;
        birthDay = inputDOB;
    }

    public void setName(String inputName)      { name = inputName;       }
    public void setGender(String inputGender)  { gender = inputGender;   }
    public void setAddress(String inputAddress){ address = inputAddress; }

    public String    getName()    { return name;    }
    public String    getGender()  { return gender;  }
    public String    getAddress() { return address; }
    public LocalDate getBirthDay(){ return birthDay;}

}
