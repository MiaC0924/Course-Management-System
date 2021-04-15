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
public class stuDropProcessController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;
    @RequestMapping("/Student/Drop/Processing")
    public String drop(@RequestParam("major") String major,
                                    @RequestParam("code") int code,
                                    @RequestParam("section") Character section,
                                    @RequestParam("year") int year,
                                    @RequestParam("season") String season,
                                    Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");
        //!!!!!!!!!!!!!!!!!!!!   need find student by email
        Student stu = studentService.findStuByEmail(email);

        //!!!!!!!!!!!!!!!!          cannot get year and season
        if( studentService.dropCourse(stu.getStudentNumber(),year,season,section,major,code)){
            System.out.println("drop success");
            model.addAttribute("msg","success");
            return "dashboardStu";
        }else {
            System.out.println("drop fail");
            model.addAttribute("msg", "fail");
            return "dashboardStu";
        }
    }
}
