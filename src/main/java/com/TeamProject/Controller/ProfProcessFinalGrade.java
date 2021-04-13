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
public class ProfProcessFinalGrade {
    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;
    @RequestMapping("Professor/SubmitFinal/Processing")
    public String submitCourseGrade(@RequestParam("id") int id,
                                    @RequestParam("grade") Character grade,
                                    Model model, HttpSession session){
        //need professor find by email
        //need find courseId by course major+code+section
        int profId = 0;
        int courseId = 0;

        //need test
        if( professorService.submitFinalGradeForOne(profId , id ,courseId, grade)){
            System.out.println("submit Final success");
            model.addAttribute("msg1","success");
        }else {
            System.out.println("submit Final fail");
            model.addAttribute("msg1", "fail");
        }
        return "dashboardProf";
    }
}
