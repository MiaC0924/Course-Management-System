package com.TeamProject.Person;

import java.time.LocalDate;

public class StudentApplication {
    String name, email, gender, pw, major;
    LocalDate dob;

    public StudentApplication(String name, String gender, String email,
                              LocalDate dob, String pw, String major){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.pw = pw;
        this.major = major;
    }

    public String getEmail() {
        return email;
    }
    public String getName(){ return name; }
    public String getGender() { return gender; }
    public LocalDate getBirthday() { return dob; }
    public String getMajor(){ return major; }
    public String getPassword(){ return pw;}

    public String toString(){
        return ""+name+email+gender+pw+major+dob;
    }
}
