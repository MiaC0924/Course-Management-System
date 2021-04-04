package com.TeamProject.Dao;

import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addStudent(Student stu){
        mongoTemplate.save(stu);
    }

}