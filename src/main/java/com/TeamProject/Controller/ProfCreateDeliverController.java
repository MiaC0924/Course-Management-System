package com.TeamProject.Controller;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Professor;
import com.TeamProject.Service.AdminService;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
public class ProfCreateDeliverController {
     @Autowired
     CourseSectionDao courseSectionDao;
    @Autowired
    ProfessorService professorService;
    AdminService adminService;
    @RequestMapping("Professor/Deliver/Create")
    public String DeleteCourse(Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");
        System.out.println("this professor Email: " + email);
        /*       need findProcessorBy email                   */
         Professor prof = professorService.findProfessorByEmail(email);
        /*       need findAllCourseSectionByProfessor                                */
        ArrayList<CourseSection> csList = new ArrayList<CourseSection>();
        csList = courseSectionDao.getAllCourseByProf(prof);

        //for test only

        //find all CourseSection
        /*          Test Area                       */
//        University university = new University("Carleton");
//        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
//        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
//        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
//        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
//        CourseSection comp3005A = new CourseSection(comp3005, 'A', 2021, "Winter");
//
//        csList.add(comp3004A);
//        csList.add(comp3004B);
//        csList.add(comp3005A);

        /*             Test Area                  */
        model.addAttribute("courses" , csList);
        return "createDeliverProf";
    }
}
