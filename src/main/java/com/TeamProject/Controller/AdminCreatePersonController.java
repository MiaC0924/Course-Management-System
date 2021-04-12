package com.TeamProject.Controller;

import com.TeamProject.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminCreatePersonController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/Admin/Person/Create/Processing")
    public String addCourse(@RequestParam("btnradio") String btnradio,
                            @RequestParam("firstN") String firstN,
                            @RequestParam("lastN") String lastN,
                            @RequestParam("gender") String gender,
                            @RequestParam("major") String major,
                            @RequestParam("birthDay") String birthDay,
                            @RequestParam("address") String address,
                            Model model, HttpSession session){
        Boolean flag1 = false;


        /*                    String -> LocalDate                                              */
        String str = birthDay;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str, fmt);
        /*                    String -> LocalDate                                              */





        System.out.println("Create Course:  btnradio: " + btnradio + " , firstN: " + firstN +
                " , lastN: " + lastN  + " ,Gender: " + gender + " Major: " + major + " BirthDay: " + date +
                "Address: " + address);

        // @Bug  !!!    createProfessor()                   createStudent() !!!       //
        // @Bug  !!!    createProfessor()                   createStudent() !!!       //
        // @Bug  !!!    createProfessor()                   createStudent() !!!       //
        // @Bug  !!!    createProfessor()                   createStudent() !!!       //
        if(btnradio.equals("Professor")){
            if( true /*adminService.createProfessor() */){
                flag1 = true;
            }else {
                flag1 = false;
            }
        }else if(btnradio.equals("Student")){
            if( true /* adminService.createStudent() */){
                flag1 = true;
            }else{
                flag1 = false;
            }

        }



        if(flag1){
            System.out.println("Create Course Success");
            model.addAttribute("msg","success");
        }else{
            System.out.println("Create Course fail");
            model.addAttribute("msg","fail");
        }
        return "createPersonAdmin";
    }
}
