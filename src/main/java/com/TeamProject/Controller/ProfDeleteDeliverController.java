package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Service.CourseSectionService;
import com.TeamProject.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProfDeleteDeliverController {
    @Autowired
    ProfessorService professorService;
    @Autowired
    CourseSectionService courseSectionService;


    @RequestMapping("/Professor/Deliver/Select_Delete/Delete/Processing")
    public String deleteDeliverable(Model model,  HttpSession session){

        String email = (String)session.getAttribute("loginUser");
        // find professor by this email
        // professorService.find professor by email
        Professor prof = professorService.findProfessorByEmail(email);

        if(prof ==null){
            return "dashboardProf";
        }else{
            int cid = (int) session.getAttribute("id");

            CourseSection cs = courseSectionService.getCourseById(cid);

            if(cs == null){
                model.addAttribute("msg","CourseSection No Found");
                return "courseSelectDeleteDeliverProf";
            }else{
                ArrayList<Deliverable> dList = new ArrayList<Deliverable>();
                //find all CourseSection
                dList = cs.getDeliverables();

                /*          Test Area                       */
//            University university = new University("Carleton");
//            Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
//            Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
//            CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
//            CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
//            CourseSection comp3005A = new CourseSection(comp3005, 'A', 2021, "Winter");
//
//            Deliverable dv1 = new Deliverable("de1",LocalDate.now());
//            Deliverable dv2 = new Deliverable("de2",LocalDate.MAX);

//            dList.add(dv1);
//            dList.add(dv2);
                /*             Test Area                  */
                model.addAttribute("deliver" , dList);
                return "deletedeliverableProf";
            }
        }
    }
}
