package com.TeamProject.Controller;

import com.TeamProject.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("btnradio") String btnradio,
                        Model model, HttpSession session){
        Boolean loginFlag = false;
        Boolean ApplyFlag = false;
        if(btnradio.equals("Professor")){
            if(loginService.professorLogin(username, password)){
                loginFlag = true;
            }if(loginService.professorApply(username, password)){
                ApplyFlag = true;
            }
        }else if(btnradio.equals("Student")){
            if(loginService.studentLogin(username, password)){
                loginFlag = true;
            }if(loginService.studentApply(username, password)){
                ApplyFlag = true;
            }
        }else if(btnradio.equals("Admin")){
            if(loginService.adminLogin(username, password)){
                loginFlag = true;
            }if(loginService.adminApply(username, password)){
                ApplyFlag = true;
            }
        }
        System.out.println(ApplyFlag);

        System.out.println(ApplyFlag);
        //need database
        if(loginFlag){
            session.setAttribute("loginUser",username);
            session.setAttribute("loginP",btnradio);

            return "redirect:/"+ btnradio + "/main";
        }else if(ApplyFlag){
            return "applyForRegister";
        }else{
            return "index";
        }
    }
}
