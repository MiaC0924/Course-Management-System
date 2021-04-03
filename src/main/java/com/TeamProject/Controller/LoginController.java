package com.TeamProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){

        //need database
        if("123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main";
        }else{
            model.addAttribute("msg","wrong password");
            return "index";
        }
    }
}
