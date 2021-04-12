package com.TeamProject.Service;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Dao.ProfessorDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.ProfessorApplication;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseSectionDao courseSectionDao;

    public boolean applyForCreation(String email,String name,String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        LocalDate DOB = LocalDate.parse(dob);
        //check if the application list is empty
        if(adminDao.findAdminById("101").getProfAppList().isEmpty()){
            adminDao.addProfosserApplications(name,gender,email,DOB,pw,major);
            return true;
        }
        else{
            //check if the application is exist already
            for(Map.Entry<String, ProfessorApplication> set : adminDao.findAdminById("101").getProfAppList().entrySet()){
                if(set.getKey() == email){
                    return false;
                }
            }
            adminDao.addProfosserApplications(name,gender,email,DOB,pw,major);
            return true;
        }
    }

    public void createDeliver(String major , int code, String section, String deliver){

    }

    public void modifyDeliver(){}

    public void deleteDeliver(){}

    public void submitListOfGrades(String major,int code,String section,String stuId,int grade){

    }

    public void submitListOfFinalGrades(String major,int code,String section,String stuId,int finalGrade){


    }

    public void submitGradeForOne(int profId,int courseSectionId,int stuId,Character grade){

        Student stu = studentDao.findStudentByStuId(stuId);
        courseSectionDao.setGradeBySectionId(courseSectionId,stu,grade);
    }
}
