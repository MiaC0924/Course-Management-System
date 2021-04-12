package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.StudentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;
    private AdminDao adminDao;

    public boolean applyForCreation(String email,String name,String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        LocalDate DOB = LocalDate.parse(dob);
        //check if the application list is empty
        if(adminDao.findAdminById("101").getStudentAppList().isEmpty()){
            adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
        else{
            //check if the application is exist already
            for(Map.Entry<String, StudentApplication> set : adminDao.findAdminById("101").getStudentAppList().entrySet()){
                if(set.getKey() == email){
                    return false;
                }
            }
            adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
    }

    public boolean registerCourse(int id,int year,String season, Character section,String majorcode,int code){
        Term term = new Term(year,season);
        Student stu = studentDao.findStudentByStuId(id);
        if(validRegisterPeriod(year,season)){
            CourseBuilding department = new Department();
            Course course = department.orderTheCourse(majorcode,code);
            CourseSection courseSection = new CourseSection(course,section,term);
            //courseSection.attachObserver(stu);
            studentDao.addCourseSectionByStuId(id,courseSection);
            return true;
        }
        else{
            return false;
        }

    }

    public boolean dropCourse(int id,int year,String season, Character section,String majorcode,int code){
        Term term = new Term(year,season);
        Student stu = studentDao.findStudentByStuId(id);

        if(validRegisterPeriod(year,season)){
            CourseBuilding department = new Department();
            Course course = department.orderTheCourse(majorcode,code);
            CourseSection courseSection = new CourseSection(course,section,term);
            //courseSection.attachObserver(stu);
            studentDao.deleteCourseSectionByStuId(id,courseSection);
            return true;
        }
        else{
            return false;
        }
    }

    public void submitDeliver(){


    }

    public boolean register(Student s, CourseSection c){
        if(validRegisterPeriod(c.getTermYear(),c.getTermSeason())){
            c.attachObserver(s);
            return true;
        }else{
            return false;
        }
    }

    public boolean validRegisterPeriod(int tY , String tS){
        if(tY == Calendar.getInstance().get(Calendar.YEAR)){
            if(tS =="Fall"){
                return true;
            }else if(tS =="Summer"){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
