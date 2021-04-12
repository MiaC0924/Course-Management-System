package com.TeamProject.Person;

import java.time.LocalDate;

public class ProfessorApplication {
    String name, email, gender, password, major;
    LocalDate birthday;

    public ProfessorApplication(String name, String gender, String email,
                              LocalDate dob, String pw, String major){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birthday = dob;
        this.password = pw;
        this.major = major;
    }

    public String getEmail() {
        return email;
    }
    public String getName(){ return name; }
    public String getGender() { return gender; }
    public LocalDate getBirthday() { return birthday; }
    public String getMajor(){ return major; }
    public String getPassword(){ return password;}
}
