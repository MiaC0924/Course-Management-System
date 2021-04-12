package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class ProfCreateDeliverHandleController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/Professor/Deliver/Create/Processing")
    public String addCourse(@RequestParam("major") String major,
                            @RequestParam("code") int code,
                            @RequestParam("section") Character section,
                            @RequestParam("deliver") String deliver,
                            @RequestParam("date") String date,
                            Model model, HttpSession session){
//Processing?major=COMP&code=1000&section=B&deliver=AA11&date=2021-04-17
        System.out.println("Create Deliver:  Major: " + major + " , Code: " + code + " , Section: " + section  + " ,deliver: " + deliver +
                " date: " + date);
        if(adminService.createCourse(major, code, section, year, season)){
            System.out.println("Create Course Success");
            model.addAttribute("msg","success");
        }else{
            System.out.println("Create Course fail");
            model.addAttribute("msg","fail");
        }
        return "createCourseAdmin";
    }



}
