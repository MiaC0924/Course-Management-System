package com.TeamProject.Controller;

import com.TeamProject.Person.Student;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class accountStuProcess {
    @Autowired
    StudentService studentService;


    @RequestMapping("Student/Account/Processing")
    public String accountStu(Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");

        Student stu =studentService.findStuByEmail(email);
        if(stu!=null){
            session.setAttribute("Name",stu.getName());
            session.setAttribute("Gender",stu.getGender());
            session.setAttribute("StudentID",stu.getStudentNumber());
            session.setAttribute("Birthday",stu.getBirthDay().toString());
        }
        return "accountStu";
    }
}
