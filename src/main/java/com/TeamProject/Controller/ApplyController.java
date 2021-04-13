package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ApplyController {

    @Autowired
    StudentService studentService;
    ProfessorService professorService;

    @RequestMapping("/Apply/")
    public String apply(Model model, HttpSession session){
        return "good";



    }


}
