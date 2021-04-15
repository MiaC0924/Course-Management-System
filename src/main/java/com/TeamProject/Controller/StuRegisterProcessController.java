package com.TeamProject.Controller;

import com.TeamProject.Person.Student;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class StuRegisterProcessController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;
    @RequestMapping("/Student/Student/Register/Processing")
    public String register(@RequestParam("major") String major,
                                    @RequestParam("code") int code,
                                    @RequestParam("section") Character section,
                                    @RequestParam("year") int year,
                                    @RequestParam("season") String season,
                                    Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");
       //!!!!!!!!!!!!!!!!!!!!   need find student by email
        Student stu = studentService.findStuByEmail(email);
        System.out.println(email);
        System.out.println(stu);
        System.out.println(""+major+code+section+year+season);


       //!!!!!!!!!!!!!!!!          cannot get year and season
        if( studentService.registerCourse(stu.getStudentNumber(),year,season,section,major,code) ){
            System.out.println("register success");
            model.addAttribute("msg1","success");
        }else {
            System.out.println("register fail");
            model.addAttribute("msg1", "fail");
        }
        return "dashboardStu";
    }
}
