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
//    @Autowired
//    ProfessorService professorService;
//
//    @Autowired
//    StudentService studentService;
//    @RequestMapping("Professor/Grades/SubmitFinal")
//    public String submitCourseGrade(Model model,  HttpSession session){
//        String major = (String)session.getAttribute("major");
//        int code = (int)session.getAttribute("code");
//        String section = (String)session.getAttribute("section");
//
//        System.out.println("This course is : major : " + major + code + section);
//
//
//
//        // !!!!!!!!!!       need a function to add all student of the course to stuList
//
//        //                 test         Area                                    //
//        ArrayList<Student> stuList = new ArrayList<Student>();
//        Student mia2 = new Student("Mia2","Female","123 Ottawa St", LocalDate.now(), "","MATH");
//        Student mia = new Student("Mia","Female","123 Ottawa St", LocalDate.now(), "","MATH");
//        stuList.add(mia);
//        stuList.add(mia2);
//
//
//        //                  test        Area                                    //
//
//
//        model.addAttribute("student" , stuList);
//        return "submitGradeFinalProf";
//    }

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
        String section = (String)session.getAttribute("section");

        System.out.println("This course is : major : " + major + code + section);



        // !!!!!!!!!!       need a function to add all student of the course to stuList

        //                 test         Area                                    //
        ArrayList<Student> stuList = new ArrayList<Student>();


        //                  test        Area                                    //


        model.addAttribute("student" , stuList);
        return "submitGradeFinalProf";
    }
}
