package com.TeamProject.Controller;

import com.TeamProject.Person.Student;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class reportStuProcess {
    @Autowired
    StudentService studentService;

    @RequestMapping("Student/Reports/Processing")
    public String report(Model model, HttpSession session){
        session.getAttribute("loginUser");
        //need a function to get all course of this student put in csList
        String email = (String)session.getAttribute("loginUser");

        Student stu =studentService.findStuByEmail(email);
        if(stu!=null){
            session.setAttribute("majorGPA",stu.getMajorGPA());
            session.setAttribute("generalGPA",stu.getOverallGPA());
        }

        //need test
        return "reportStu";
    }
}
