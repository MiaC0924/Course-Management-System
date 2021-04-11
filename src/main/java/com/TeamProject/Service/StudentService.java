package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;
    AdminDao adminDao;

    //{email:[name,gender,dob,pw,major]}
    public boolean applyForCreation(String email,String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        //need boolean addStudentApplications(e,g,d,p,m){} in adminDao
       // if(adminDao.addStudentApplications(email,gender,dob,pw,major)){
       //     return true;
       // }
       // else{
            return false;
       // }
    }

    public boolean registerCourse(int id,int year,String season, Character section,String Course,int code){
        Term term = new Term(year,season);
        Student stu = studentDao.findStudentByStuId(id);
        if(validRegisterPeriod(year,season)){
            CourseBuilding department = new Department();
            Course course = department.orderTheCourse("COMP",3004);
            CourseSection courseSection = new CourseSection(course,section,term);
            //courseSection.attachObserver(stu);
           // studentDao.addCourseByStuId(id,courseSection);
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
            Course course = department.orderTheCourse(majorcode,3004);
            CourseSection courseSection = new CourseSection(course,section,term);
            //courseSection.attachObserver(stu);
          //  studentDao.deleteCourseByStuId(id,courseSection);
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
