package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    CourseSectionDao courseSectionDao;

    @RequestMapping("Professor/SubmitFinal/Processing")
    public String deleteCourse(@RequestParam("id") int id,
                                    @RequestParam("grade") Character grade,
                                    Model model, HttpSession session){
        //need professor find by email
        //need find courseId by course major+code+section
        //need a function to get all course of this student put in csList
        String email = (String)session.getAttribute("loginUser");
        String major = (String)session.getAttribute("major");
        int code = (int)session.getAttribute("code");
        Character section = (Character) session.getAttribute("section");
        int year = (int) session.getAttribute("year");
        String season = (String) session.getAttribute("season");

        CourseSection cs = courseSectionDao.findSectionByAllInfo(major,code,section,year,season);
        if(cs!= null){
            if( professorService.submitFinalGradeForOne(email , cs ,id, grade)){
                System.out.println("submit Final success");

                System.out.println("Professor submit finalGrade for id: "+id+" : "+grade);
                model.addAttribute("msg","success");

            }else {
                System.out.println("submit Final fail");
                model.addAttribute("msg", "fail");
            }
        }else{
            System.out.println("submit Final fail");
            model.addAttribute("msg", "fail");
        }

        //need test

        return "dashboardProf";
    }
}
