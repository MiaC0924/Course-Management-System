package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProfProcessFinalGrade {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseSectionDao courseSectionDao;

    @RequestMapping("Professor/SubmitFinal/Processing")
    public String submitCourseGrade(@RequestParam("id") int id,
                                    @RequestParam("grade") Character grade,
                                    Model model, HttpSession session){
        //need professor find by email
        //need find courseId by course major+code+section

        int profId = 0;
        int courseId = 0;
        session.getAttribute("loginUser");
        session.getAttribute("major");
        session.getAttribute("code");
        session.getAttribute("section");
        session.getAttribute("year");
        session.getAttribute("season");
        //need a function to get all course of this student put in csList
        String email = (String)session.getAttribute("loginUser");
        String major = (String)session.getAttribute("major");
        int code = (int)session.getAttribute("code");
        Character section = (Character) session.getAttribute("section");
        int year = (int) session.getAttribute("year");
        String season = (String) session.getAttribute("season");

        CourseSection cs = courseSectionDao.findSectionByAllInfo(major,code,section,year,season);
        System.out.println(major+code+section+year+season);



        //need test
        if( professorService.submitFinalGradeForOne(email , cs ,id, grade)){
            System.out.println("submit Final success");
            System.out.println("Professor submit finalGrade for id: "+id+" : "+grade);
            model.addAttribute("msg1","success");
        }else {
            System.out.println("submit Final fail");
            model.addAttribute("msg1", "fail");
        }
        return "dashboardProf";
    }
}
