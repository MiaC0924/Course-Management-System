package com.TeamProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class signOutController {


    @RequestMapping("/signOut")
    public String signOut(Model model, HttpSession session){
        session.removeAttribute("loginP");
        session.removeAttribute("loginUser");
        return "index";
    }
}
