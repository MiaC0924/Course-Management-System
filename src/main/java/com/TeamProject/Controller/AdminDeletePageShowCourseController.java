package com.TeamProject.Controller;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.CourseSectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class AdminDeletePageShowCourseController {
    // @Autowired a courseSection service


    @RequestMapping("Admin/Courses/Delete")
    public String DeleteCourse(Model model){
        ArrayList<CourseSection> csList = new ArrayList<CourseSection>();
       //find all CourseSection

        /*          Test Area                       */
        University university = new University("Carleton");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021,"Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021,"Winter");
        CourseSection comp3005A = new CourseSection(comp3005, 'A', 2021,"Winter");

        csList.add(comp3004A);
        csList.add(comp3004B);
        csList.add(comp3005A);




        /*             Test Area                  */
        model.addAttribute("courses" , csList);
        return "deleteCourseAdmin";
    }
}
