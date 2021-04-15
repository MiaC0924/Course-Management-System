package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProfSubmitFinalGradeController {

    @Autowired
    ProfessorService professorService;
    @Autowired
    CourseSectionService courseSectionService;

    @RequestMapping("/Professor/Grades/SubmitFinal")
    public String selectCourse(@RequestParam("id") int id,
                               Model model, HttpSession session){
        String email =(String) session.getAttribute("loginUser");


        CourseSection cs = courseSectionService.getCourseById(id);

        if(cs==null){
            System.out.println("Wrong Id");
            model.addAttribute("msg" ,"fail");
            return "courseSelectGradeFinalProf";
        }else{
                    System.out.println("Student list"+cs.getStudentList());
                    model.addAttribute("student",cs.getStudentList());
                    model.addAttribute("msg" ,"Success");
                    session.setAttribute("major" , cs.getMajor());
                    session.setAttribute("code" , cs.getCode());
                    session.setAttribute("section" , cs.getSection());
                    session.setAttribute("year" , cs.getTermYear());
                    session.setAttribute("season" , cs.getTermSeason());
                    return "redirect:/Professor/SubmitFinal";
        }
    }



}
