package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
@Controller
public class RegisterController {
    @Autowired
    private StudentDao stuDao;

    public RegisterController(){

    }

    public boolean register(Student s, CourseSection c){
        if(validRegisterPeriod(c.getTermYear(), c.getTermSeason())){
            c.attachObserver(s);
            return true;
        }else{
            return false;
        }
    }

    public boolean assignCourse(Professor p,CourseSection c){
        c.attachObserver(p);
        return true;
    }

    public boolean validRegisterPeriod(int cYear, String cSeason){
        if(cYear == Calendar.getInstance().get(Calendar.YEAR)){
            if(cSeason.equals("Fall")){
                return true;
            }else return cSeason.equals("Summer");
        }else{
            return false;
        }
    }

    public void AdminDenied(Admin a,Student s, CourseSection c){
        c.detachObserver(s);
    }


}