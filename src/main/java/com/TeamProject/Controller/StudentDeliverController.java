package com.TeamProject.Controller;

import com.TeamProject.Dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentDeliverController {
    @Autowired
    private StudentDao stuDao;

    public StudentDeliverController(){

    }

}
