package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Service.AdminService;
import com.TeamProject.Service.ProfessorService;
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
public class ProfSubmitFinalGradeController {

//    @Autowired
//    AdminService adminService;
//    ProfessorService professorService;
//    CourseSectionDao courseSectionDao;
//    //need courseSectionService
//    @RequestMapping("/Professor/Grades/FinalGrade")
//    public String selectCourse(Model model, HttpSession session){
//        CourseSection cs = courseSectionDao.findSectionById((Integer) session.getAttribute("id"));
//
//        System.out.println("Professor submit finalGrade :  Major: " + cs.getMajor() + " , Code: " + cs.getCode() + " , Section: " + cs.getSectionID());
//
//        if(/*courseSectionService.findCourseSection(string major, int code)*/ true){
//            session.setAttribute("major" , cs.getMajor());
//            session.setAttribute("code" , cs.getCode());
//            session.setAttribute("section" , cs.getSectionID());
//            return "redirect:/Professor/Grades/SubmitFinal";
//        }else{
//
//            System.out.println("Find Course fail");
//            model.addAttribute("msg1","fail");
//            return "dashboardProf";
//        }
//
//    }

    @Autowired
    AdminService adminService;
    ProfessorService professorService;
    //need courseSectionService
    @RequestMapping("/Professor/FinalGrade")
    public String submitFinalGrade(@RequestParam("course") String major,
                                   @RequestParam("code") int code,
                                   @RequestParam("section") String section,
                                   Model model, HttpSession session){



        System.out.println("Professor submit finalGrade :  Major: " + major + " , Code: " + code + " , Section: " + section);

        if(/*courseSectionService.findCourseSection(string major, int code)*/ true){
            session.setAttribute("major" , major);
            session.setAttribute("code" , code);
            session.setAttribute("section" , section);
            return "redirect:/Professor/SubmitFinal";
        }else{

            System.out.println("Find Course fail");
            model.addAttribute("msg1","fail");
            return "dashboardProf";
        }

    }



}
