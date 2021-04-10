package com.TeamProject.Controller;

import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Student;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class LoginController {
  //  @Autowired
   // private StudentDao stuDao;
    private StudentService studentService;


    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("btnradio") String btnradio,
                        Model model, HttpSession session){

        //need database
        if("123456".equals(password)){
            session.setAttribute("loginUser",username);
            session.setAttribute("loginP",btnradio);
            //stuDao.addStudent(new Student("Gura","F",username, LocalDate.now(),"MATH"));

            return "redirect:/"+ btnradio + "/main";
        }else{
            model.addAttribute("msg","wrong password");
            return "index";
        }
    }
}
