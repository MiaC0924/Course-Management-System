package com.TeamProject.Service;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;
    StudentDao studentDao;


    //create student
    public void createStudent(){
        if(adminDao.findAdminById("101").getisEmpty()){
            System.out.println("no application");
        }
        else{
            //from the hash map{email:[name,gender,dob,pw,major]} create students
            for(Map.Entry<String, ArrayList<String>> entry : admin.getStudentApplications().entrySet()){
                String email =  entry.getKey();
                for(int i =0;i<entry.getValue().size();i++){
                    String name = entry.getValue().get(0);
                    String gender = entry.getValue().get(1);
                    String dob = entry.getValue().get(2);
                    String pw = entry.getValue().get(3);
                    String major = entry.getValue().get(4);
                    LocalDate DOB = LocalDate.parse(dob);
                    Student stu = new Student(name,gender,email,DOB,pw,major);
                    //send new student to the DATABASE using studentDao
                    studentDao.addStudent(stu);
                    //delete the student information from the hashmap after saved
                    admin.getStudentApplications().remove(email);//adminDao.deleteStudentApplicationByEmail(email);
                    //update database using adminDao
                    adminDao.updateByEmail(admin);
                }
            }
        }
    }

}
