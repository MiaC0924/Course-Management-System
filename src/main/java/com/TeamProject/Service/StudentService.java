package com.TeamProject.Service;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
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
        if(adminDao.addStudentApplications(email,gender,dob,pw,major)){
            return true;
        }
        else{
            return false;
        }
    }

    public void registerCourse(){
    }

    public void dropCourse(){

    }

    public void submit(){

    }

    public boolean register(Student s, CourseSection c){
        if(validRegisterPeriod(c.getTerm())){
            c.attachObserver(s);
            return true;
        }else{
            return false;
        }
    }

    public boolean assignCourse(Professor p, CourseSection c){
        c.attachObserver(p);
        return true;
    }

    public boolean validRegisterPeriod(Term t){
        if(t.getYear() == Calendar.getInstance().get(Calendar.YEAR)){
            if(t.getSeason()=="Fall"){
                return true;
            }else if(t.getSeason()=="Summer"){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
