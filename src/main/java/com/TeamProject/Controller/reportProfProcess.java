package com.TeamProject.Controller;

import com.TeamProject.Person.Professor;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class reportProfProcess {
    @Autowired
    ProfessorService professorService;

    @RequestMapping("Professor/Reports/Processing")
    public String report(Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");

        Professor prof =professorService.findProfessorByEmail(email);
        if(prof!=null){
            session.setAttribute("OverPassRate",prof.getPassRateOverAll());
            session.setAttribute("GeneralPassRate",prof.getPassRateOfCurr());
        }
        return "reportProf";
    }
}
