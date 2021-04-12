package com.TeamProject.Person;

import com.TeamProject.Observer.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;

@Document(collection = "Admin_table")
public class Admin extends Person{
    private static int countID = 101;
    @Id
    private String adminID;
    private boolean admitted;
    private HashMap<String, StudentApplication> studentAppList;
    private HashMap<String, ProfessorApplication> profApplist;


    public Admin(){
        super();
        this.setName("Admin");
        adminID  = countID + "";
        password = "";
        admitted = false;
        ++countID;
        studentAppList = new HashMap<>();
        profApplist = new HashMap<>();
    }

    public Admin(String inputName, String inputGender, String inputAddress, LocalDate inputDOB, String inputPW){
        super(inputName, inputGender, inputAddress, inputDOB, inputPW);
        adminID  = countID + "";
        admitted = false;
        this.password = inputPW;
        ++countID;
    }

    public String getAdminID() { return adminID;  }
    public HashMap<String,StudentApplication> getStudentAppList(){ return studentAppList; }
    public HashMap<String,ProfessorApplication> getProfAppList(){ return profApplist; }


    public boolean addStudentApp(StudentApplication app){
        if(studentAppList.get(app.getEmail()) != null){
            return false;
        }
        studentAppList.put(app.email, app);
        return true;
    }
    public boolean addProfessorApp(ProfessorApplication app){
        if(profApplist.get(app.getEmail()) != null){
            return false;
        }
        profApplist.put(app.email, app);
        return true;
    }

    public boolean deleteStudentApp(String email){
        if(studentAppList.get(email) == null){
            return false;
        }
        studentAppList.remove(email);
        return true;
    }

    public boolean deleteProfessorApp(String email){
        if(profApplist.get(email) == null){
            return false;
        }
        profApplist.remove(email);
        return true;
    }

    @Override
    public void update(Subject s) {
        //NOTHING;
    }
}
