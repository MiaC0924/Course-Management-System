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
    private HashMap<Integer, StudentApplication> studentAppList;
    private HashMap<Integer, ProfessorApplication> profApplist;


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
        studentAppList = new HashMap<>();
        profApplist = new HashMap<>();
    }

    public String getAdminID() { return adminID;  }
    public HashMap<Integer,StudentApplication> getStudentAppList(){ return studentAppList; }
    public HashMap<Integer,ProfessorApplication> getProfAppList(){ return profApplist; }


    public boolean addStudentApp(StudentApplication app){
        if(studentAppList.get(app.getId()) != null){
            return false;
        }
        studentAppList.put(app.getId(), app);
        return true;
    }
    public boolean addProfessorApp(ProfessorApplication app){
        if(profApplist.get(app.getId()) != null){
            return false;
        }
        profApplist.put(app.getId(), app);
        return true;
    }

    public boolean deleteStudentApp(int applicationId){
        if(studentAppList.get(applicationId) == null){
            return false;
        }
        studentAppList.remove(applicationId);
        return true;
    }

    public boolean deleteProfessorApp(int applicationId){
        if(profApplist.get(applicationId) == null){
            return false;
        }
        profApplist.remove(applicationId);
        return true;
    }

    @Override
    public void update(Subject s) {
        //NOTHING;
    }
}
