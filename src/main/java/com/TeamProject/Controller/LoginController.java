package com.TeamProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    @ResponseBody
    public String login(String username, String password){
        return "Username: " + username + " Password: " + password;
    }
}
