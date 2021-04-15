package com.TeamProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class uploadDeliver {

    @RequestMapping("/test2")
    public String upload(){
        //username: kevinkong0530@gmail.com password: 3301Douglas!
        return "uploadDeliver";
    }
}
