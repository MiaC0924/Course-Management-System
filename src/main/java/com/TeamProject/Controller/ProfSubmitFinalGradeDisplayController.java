package com.TeamProject.Controller;

import com.TeamProject.Person.Student;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProfSubmitFinalGradeDisplayController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseSectionService courseService;
    @RequestMapping("Professor/SubmitFinal")
    public String submitCourseGrade(Model model,  HttpSession session){
        String major = (String)session.getAttribute("major");
        int code = (int)session.getAttribute("code");

        Character section = (Character) session.getAttribute("section");
        int year = (int)session.getAttribute("year");
        String season = (String)session.getAttribute("season");


        System.out.println("This course is : major : " + major + code + section);



        // !!!!!!!!!!       need a function to add all student of the course to stuList

        //                 test         Area                                    //
        ArrayList<Student> stuList = new ArrayList<Student>();


        //                  test        Area                                    //


        model.addAttribute("student" , stuList);
        return "submitGradeFinalProf";
    }
}
