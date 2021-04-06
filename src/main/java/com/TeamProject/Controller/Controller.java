package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Student;

import java.util.Calendar;

public class Controller {

    public Controller(){

    }

    public boolean register(Student s, CourseSection c){
        if(validPeriod(c.getTerm())){
            c.attachObserver(s);
            return true;
        }else{
            return false;
        }
    }

    public boolean validPeriod(Term t){
        if(t.getYear() == Calendar.getInstance().get(Calendar.YEAR)){
            if(t.getSeason()=="Fall"){
                return true;
            }else if(t.getSeason()=="Summer"){
                return false;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void AdminDenied(Admin a,Student s, CourseSection c){
        c.detachObserver(s);
    }

    public boolean dropCourse(Student s,CourseSection c){
        return false;
    }
}
