package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProfSelectCourseProcess {
    @Autowired
    ProfessorService professorService;

    @Autowired
    CourseSectionService courseSectionService;

    @RequestMapping("/Professor/Grades/SelectCourse/Processing")
    public String selectCourse(Model model, HttpSession session){
        String email =(String) session.getAttribute("loginUser");
        Professor prof = professorService.findProfessorByEmail(email);

        if(prof==null){
            return "dashboardProf";
        }else{
            ArrayList<CourseSection> csList = new ArrayList<CourseSection>();
            csList = courseSectionService.getAllCourseByProf(prof);
            model.addAttribute("courses" , csList);
            return "courseSelectGradeFinalProf";
        }
    }
}
