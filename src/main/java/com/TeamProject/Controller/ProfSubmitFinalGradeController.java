package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Service.AdminService;
import com.TeamProject.Service.CourseSectionService;
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
    @Autowired
    ProfessorService professorService;
    @Autowired
    CourseSectionService courseSectionService;
    @RequestMapping("/Professor/FinalGrade")
    public String submitFinalGrade(@RequestParam("course") String major,
                                   @RequestParam("code") int code,
                                   @RequestParam("section") Character section,
                                   @RequestParam("year") int year,
                                   @RequestParam("season") String season,
                                   Model model, HttpSession session){

        System.out.println("Professor submit finalGrade :  Major: " + major + " , Code: " + code + " , Section: " + section);
//        courseSectionService.findCourseSection(major,code)!=null
        if(true){
            session.setAttribute("major" , major);
            session.setAttribute("code" , code);
            session.setAttribute("section" , section);
            session.setAttribute("year" , year);
            session.setAttribute("season" , season);
            return "redirect:/Professor/SubmitFinal";
        }else{

            System.out.println("Find Course fail");
            model.addAttribute("msg1","fail");
            return "dashboardProf";
        }

    }



}
