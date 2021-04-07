package com.TeamProject.Person;

import com.TeamProject.Observer.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Administer_table")
public class Admin extends Person{
    private static int countID = 101;
    @Id
    private String adminID;
    private boolean admitted;

    public Admin(){
        super();
        this.setName("Admin");
        adminID  = countID + "";
        password = "";
        admitted = false;
        ++countID;
    }

    public Admin(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputPW){
        super(inputName, inputGender, inputAddress, inputDOB, inputPW);
        adminID  = countID + "";
        admitted = false;
        this.password = inputPW;
        ++countID;
    }

    public String getAdminID() { return adminID;  }

    @Override
    public void update(Subject s) {
        //NOTHING;
    }
}
