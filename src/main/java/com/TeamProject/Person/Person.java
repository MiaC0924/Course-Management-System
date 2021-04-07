package com.TeamProject.Person;

import com.TeamProject.Observer.Observer;

import java.time.LocalDate;

public abstract class Person  implements Observer {
    protected String name, gender, address;
    protected LocalDate birthDay;
    protected String username;
    protected String password;

    public Person(){
        name = "";
        gender = "";
        address = "";
        birthDay = LocalDate.parse("9999-01-01");
        username = "1";
        password = "123456";
    }

    public Person(String inputName, String inputGender, String inputAddress, LocalDate inputDOB){
        name     = inputName;
        gender   = inputGender;
        address  = inputAddress;
        birthDay = inputDOB;

        username = "1";
        password = "123456";
    }

    public void setName(String inputName)      { name = inputName;       }
    public void setGender(String inputGender)  { gender = inputGender;   }
    public void setAddress(String inputAddress){ address = inputAddress; }
    public void setPassword(String inputPassword){ password = inputPassword; }
    public void setUsername(String inputUserName){ username = inputUserName; }

    public String    getUsername(){ return username; }
    public String    getName()    { return name;    }
    public String    getGender()  { return gender;  }
    public String    getAddress() { return address; }
    public String    getPassword(){ return password; }
    public LocalDate getBirthDay(){ return birthDay; }


}
