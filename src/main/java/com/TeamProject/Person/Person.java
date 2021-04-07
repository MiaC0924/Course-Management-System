package com.TeamProject.Person;

import com.TeamProject.Observer.Observer;

import java.time.LocalDate;

public abstract class Person  implements Observer {
    protected String name, gender, email, password;
    protected LocalDate birthDay;

    public Person(){
        name = "";
        gender = "";
        email = "";
        password = "";
        birthDay = LocalDate.parse("9999-01-01");
    }

    public Person(String inputName, String inputGender, String inputEmail, LocalDate inputDOB, String inputPW){
        name     = inputName;
        gender   = inputGender;
        email    = inputEmail;
        birthDay = inputDOB;
        password = inputPW;
    }

    public void setName(String inputName)      { name = inputName;       }
    public void setGender(String inputGender)  { gender = inputGender;   }
    public void setAddress(String inputEmail)  { email = inputEmail;     }
    public void setPassword(String inputPW)    { password = inputPW;     }

    public String    getName()    { return name;    }
    public String    getGender()  { return gender;  }
    public String    getEmail() { return email; }
    public LocalDate getBirthDay(){ return birthDay;}

    public boolean checkPassword(String inputPw) {
        if(inputPw.equals(password))
            return true;
        return false;
    }

}
