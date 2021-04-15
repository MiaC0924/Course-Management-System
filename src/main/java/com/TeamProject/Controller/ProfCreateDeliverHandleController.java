package com.TeamProject.Controller;

import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ProfCreateDeliverHandleController {

    @Autowired
    ProfessorService professorService;

    @RequestMapping("/Professor/Deliver/Create/Processing")
    public String addCourse(@RequestParam("major") String major,
                            @RequestParam("code") int code,
                            @RequestParam("section") Character section,
                            @RequestParam("year") int year,
                            @RequestParam("season") String season,
                            @RequestParam("deliver") String deliver,
                            @RequestParam("date") String date,
                            Model model, HttpSession session){
        // convert year
        String str = date;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(str, fmt);
        // convert year
        System.out.println("Create Deliver:  Major: " + major + " , Code: " + code + " , Section: " + section  + " ,deliver: " + deliver +
                " date: " + date1);

        // Bug !!! professorService.createDeliver(major , code,section , deliver) no return value !!! //

        if( professorService.createDeliver(major , code, section,year,season , deliver,date1)){
            System.out.println("Create Deliver Success");
            model.addAttribute("msg","success");
        }else{
            System.out.println("Create Deliver fail");
            model.addAttribute("msg","fail");
        }
        return "dashboardProf";
    }



}
