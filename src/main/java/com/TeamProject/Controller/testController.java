package com.TeamProject.Controller;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.CourseDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class testController {
    @Autowired
    CourseDao mongoDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    AdminDao adminDao;



    @RequestMapping("/test1")
    public String test(){
        University university = new University("Carleton University");
        Course math2107 = university.getDepartments().orderTheCourse("MATH",2107);
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        Course math1007 = university.getDepartments().orderTheCourse("MATH",1007);
        Student mia = new Student("Mia","Female","123 Ottawa St", LocalDate.now(), "","MATH");

        mongoDao.addCourse(comp3005);
        mongoDao.addCourse(math1007);
        math2107 = mongoDao.findAdminByNameAndCode("MATH",2107);
       // System.out.println(math2107.getCourseName());
       // System.out.println(math2107.getCode());
        CourseSection cs = new CourseSection(math2107, 'a',2021 , "Winter");
        CourseSection cs1 = new CourseSection(comp3004 , 'B' ,2022 , "Winter");
        mia.addSection(cs);


        studentDao.addStudent(mia);
        mia.addSection(cs1);
       // studentDao.addStudent(mia);

       // studentDao.addStudentCourseSectionByStuId(mia.getStudentNumber() , cs1);
        return "good";
    }
}
