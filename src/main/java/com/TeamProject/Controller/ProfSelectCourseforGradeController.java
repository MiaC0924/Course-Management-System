package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Service.CourseSectionService;
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
public class ProfSelectCourseforGradeController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    CourseSectionService courseSectionService;

    @RequestMapping("/Professor/Grades/SelectCourse")
    public String selectCourse(@RequestParam("id") int id,
                                Model model, HttpSession session){
        String email =(String) session.getAttribute("loginUser");
        Professor prof = professorService.findProfessorByEmail(email);


        CourseSection cs = courseSectionService.getCourseById(id);

        if(cs==null){
            System.out.println("Wrong Id");
            model.addAttribute("msg1" ,"fail");
            return "courseSelectGradeFinalProf";
        }else{
            ArrayList<CourseSection> cslist = (ArrayList<CourseSection>) model.getAttribute("courses");
            for(CourseSection courseSection: cslist){
                if(cs.getSectionID()==courseSection.getSectionID()){
                    System.out.println("Success Id");
                    model.addAttribute("msg1" ,"Success");
                    session.setAttribute("id",id);
                    return "submitGradeFinalProf";
                }
            }
            System.out.println("Wrong Id");
            model.addAttribute("msg1" ,"fail");
            return "courseSelectGradeFinalProf";


        }
    }
}
