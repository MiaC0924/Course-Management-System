package com.TeamProject.Controller;

import com.TeamProject.Person.Professor;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class accountProfProcess {
    @Autowired
    ProfessorService professorService;

    @RequestMapping("Professor/Account/Processing")
    public String accountProf(Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");

        Professor prof =professorService.findProfessorByEmail(email);
        if(prof!=null){
            session.setAttribute("Name",prof.getName());
            session.setAttribute("Gender",prof.getGender());
            session.setAttribute("ProfessorID",prof.getProfID());
            session.setAttribute("Birthday",prof.getBirthDay().toString());
        }
        return "accountProf";
    }
}
