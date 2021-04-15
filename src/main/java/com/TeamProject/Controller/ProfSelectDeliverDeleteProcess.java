package com.TeamProject.Controller;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProfSelectDeliverDeleteProcess {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseSectionService courseService;
    @RequestMapping("/Professor/Deliver/Select_Delete/Processing")
    public String delete(Model model,  HttpSession session){
        String email = (String) session.getAttribute("loginUser");
        Professor prof = professorService.findProfessorByEmail(email);
        //                 test         Area                                    //
        if(prof==null){
            return "dashboardProf";
        }else{
            ArrayList<CourseSection> csList = new ArrayList<CourseSection>();
            csList = courseService.getAllCourseByProf(prof);

            //                  test        Area                                    //
            model.addAttribute("courses" , csList);
            return "courseSelectDeleteDeliverProf";
        }

    }
}
