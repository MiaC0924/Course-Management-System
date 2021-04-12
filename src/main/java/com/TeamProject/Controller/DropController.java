package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Calendar;

@Controller
public class DropController {
    @Autowired
    private StudentDao stuDao;

    public DropController(){

    }

    public int validDropPeriod(int cYear, String cSeason){
        if(cYear == Calendar.getInstance().get(Calendar.YEAR)){
            if(cSeason.equals("Fall")){
                return 1;
            }else if(cSeason.equals("Summer")){
                return 0;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    public boolean dropCourse(Student s, CourseSection c){
        if(validDropPeriod(c.getTermYear(), c.getTermSeason()) == 0&&c.getObservers().contains(s)){
            c.detachObserver(s);
            s.removeSection(c);
            return true;
        }else if(validDropPeriod(c.getTermYear(), c.getTermSeason()) > 0&&c.getObservers().contains(s)){
            c.detachObserver(s);
            return true;
        }else{
            return false;
        }
    }
}
