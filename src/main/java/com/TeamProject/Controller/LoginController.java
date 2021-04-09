package com.TeamProject.Controller;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class LoginController {
    @Autowired
    private StudentDao stuDao;


    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("btnradio") String btnradio,
                        Model model, HttpSession session){

        //need database
        if("123456".equals(password)){
            session.setAttribute("loginUser",username);
            session.setAttribute("loginP",btnradio);






//
         Student stu = new Student("Gura2","F",username, LocalDate.now(),"123","MATH");
//           // stuDao.addStudent(stu);
//            //stu = stuDao.findStudentByStuId(stu.getStudentNumber());
//            System.out.println(stu);
//           // stuDao.updateStudentMajorGPAByStuId(100000001,100.0);
//            Professor john = new Professor();
            University university = new University("Carleton");
           Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
//            Course comp3004 = new Course("COMP", 3004);
           Term winter2021 = new Term(2021, "Winter");
            CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);
//
            comp3004A.addStudent(stu);
            comp3004A.addGrade(stu,'A');
//            stu.addCourse(comp3004A);
//
//           // stuDao.deleteStudentByStuId(100000001);
            stuDao.addStudent(stu);
           //stuDao.updateStudentCourseSectionByStuId(100000001, comp3004A);
            return "redirect:/"+ btnradio + "/main";
        }else{
            model.addAttribute("msg","wrong password");
            return "index";
        }
    }
}
