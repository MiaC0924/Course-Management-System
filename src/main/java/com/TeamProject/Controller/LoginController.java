package com.TeamProject.Controller;

import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.AdminRepository;
import com.TeamProject.Person.Student;
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
    @Autowired
    private StudentDao stuDao;
    private AdminDao   admDao;
    private AdminRepository adminRepository;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("btnradio") String btnradio,
                        Model model, HttpSession session){

        if(valid(btnradio, username, password)){
            session.setAttribute("loginId",username);
            session.setAttribute("loginP",btnradio);

            return "redirect:/"+ btnradio + "/main";
        }else{
            model.addAttribute("msg","wrong password");
            return "index";
        }
    }

    private boolean valid(String mood, String username, String inputPW) {
        if (mood == "Admin") {
            Admin adm = admDao.findAdminById(username);
            if (adm != null && adm.checkPassword(inputPW))
                return true;
//        } else if (mood == "Student"){
//
//        } else if (mood == "Professor"){

        }
        return false;
    }
}
