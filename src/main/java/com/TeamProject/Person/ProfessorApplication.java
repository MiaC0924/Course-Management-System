package com.TeamProject.Person;

import java.time.LocalDate;

public class ProfessorApplication {
    private static int countID = 1;
    private int id;
    String name, email, gender, pw, major;
    LocalDate dob;

    public ProfessorApplication(String name, String gender, String email,
                              LocalDate dob, String pw, String major){
        id = countID;
        ++countID;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.pw = pw;
        this.major = major;
    }

    public int getId(){ return id; }
    public String getEmail() {
        return email;
    }
    public String getName(){ return name; }
    public String getGender() { return gender; }
    public LocalDate getBirthday() { return dob; }
    public String getMajor(){ return major; }
    public String getPassword(){ return pw;}
}
