package com.TeamProject.Controller;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Deliverable;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;

@Controller
public class ProfDeleteDeliverable {
    @Autowired
    ProfessorService professorService;

    @Autowired
    CourseSectionService courseSectionService;

    @RequestMapping("/Professor/Deliver/Select_Delete/Delete/")
    public String deleteCourse(@RequestParam("name") String name,
                                    Model model, HttpSession session){

        String major = (String)session.getAttribute("major");
        int code = (int)session.getAttribute("code");
        Character section = (Character)session.getAttribute("section");
        int year = (int)session.getAttribute("year");
        String season = (String)session.getAttribute("season");

        CourseSection cs = courseSectionService.findCourseSection(major,code,section,year,season);
        System.out.println(cs);

        if(cs == null){
            return "courseSelectDeleteDeliverProf";
        }else{
            if(professorService.removeDeliverable(cs,name)){
                model.addAttribute("msg","Success removed");
                return "dashboardProf";
            }else{
                model.addAttribute("msg","No found");
                return "deletedeliverableProf";
            }

        }



    }
}
