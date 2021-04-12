package com.TeamProject.Person;

import java.time.LocalDate;

public class StudentApplication {
    String name, email, gender, password, major;
    LocalDate birthday;

    public StudentApplication(String name, String gender, String email,
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
}
