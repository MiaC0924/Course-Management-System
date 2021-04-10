package com.TeamProject.Controller;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.University;
import com.TeamProject.Dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class testController {
    @Autowired
    CourseDao mongoDao;




    @RequestMapping("/test1")
    public String test(){
        University university = new University("Carleton University");
        Course math2107 = university.getDepartments().orderTheCourse("MATH",2107);
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        Course math1007 = university.getDepartments().orderTheCourse("MATH",1007);

        mongoDao.addCourse(comp3005);
        mongoDao.addCourse(math1007);
        math2107 = mongoDao.findAdminByNameAndCode("MATH",2107);
        System.out.println(math2107.getCourseName());
        System.out.println(math2107.getCode());
       return "good";
    }
}
