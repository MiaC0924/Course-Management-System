package com.TeamProject.Controller;

import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ApplyController {

    @Autowired
    StudentService studentService;
    @Autowired
    ProfessorService professorService;

    @RequestMapping("/Apply")
    public String apply(@RequestParam("btnradio") String btnradio,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("firstN") String firstN,
                        @RequestParam("lastN") String lastN,
                        @RequestParam("gender") String gender,
                        @RequestParam("major") String major,
                        @RequestParam("birthDay") String birthDay,
                        Model model, HttpSession session){

        System.out.println("Apply Account:  firstN: " + firstN + " , lastN: " + lastN + " , gender: " + gender  + " ,major: " + major + " birthDay: " + birthDay);


        Boolean ApplyFlag = false;
        if(btnradio.equals("Professor")){
            if(professorService.applyforCreation(email,firstN+lastN,gender,birthDay,password,major)){
                ApplyFlag=true;
            }
        }else if(btnradio.equals("Student")){
            if(studentService.applyForCreation(email,firstN+lastN,gender,birthDay,password,major)){
                ApplyFlag=true;
            }
        }

        if(ApplyFlag){
            model.addAttribute("msg","Apply successful");
            return "index";
        }else{
            model.addAttribute("msg","wrong username/password");
            return "index";
        }
    }
}
