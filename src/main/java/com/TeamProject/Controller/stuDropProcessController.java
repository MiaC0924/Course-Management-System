package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class stuDropProcessController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;
    @RequestMapping("/Student/Drop/Processing")
    public String submitCourseGrade(@RequestParam("major") String major,
                                    @RequestParam("code") int code,
                                    @RequestParam("section") Character section,
                                    @RequestParam("year") int year,
                                    @RequestParam("season") String season,
                                    Model model, HttpSession session){
        String email = (String)session.getAttribute("loginUser");
        //!!!!!!!!!!!!!!!!!!!!   need find student by email
        Student stu = studentService.findStuByEmail(email);

        //!!!!!!!!!!!!!!!!          cannot get year and season
        if( studentService.dropCourse(stu.getStudentNumber(),year,season,section,major,code)){
            System.out.println("drop success");
            model.addAttribute("msg1","success");
        }else {
            System.out.println("drop fail");
            model.addAttribute("msg1", "fail");
        }
        return "dashboardStu";
    }
}
