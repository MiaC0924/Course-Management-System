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
    public boolean applyForCreation(String email,String name, String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        if(adminDao.addStudentApplications(email,name,gender,dob,pw,major)){
            return true;
        }
        else{
            return false;
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
