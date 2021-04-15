package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminCreateCourseController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/Admin/Courses/Create/Processing")
    public String addCourse(@RequestParam("major") String major,
                            @RequestParam("code") int code,
                            @RequestParam("section") Character section,
                            @RequestParam("year") int year,
                            @RequestParam("Season") String season,
                            Model model, HttpSession session){

        System.out.println("Create Course:  Major: " + major + " , Code: " + code + " , Section: " + section  + " ,Year: " + year + " Season: " + season);
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
