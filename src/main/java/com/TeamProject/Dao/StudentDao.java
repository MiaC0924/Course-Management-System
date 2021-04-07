package com.TeamProject.Dao;

import com.TeamProject.Person.Student;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class StudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean addStudent(Student stu){
        if(findStudentByEmail(stu.getAddress())!= null){
            return false;
        }
        mongoTemplate.save(stu)
        return true;
    }
    public Student findStudentByEmail(String email){
        if(email == null){
            return null;
        }
        Query query = new Query((Criteria.where("address").is(email)));
        Student stu = mongoTemplate.findOne(query , Student.class);
        return stu;
    }

    public boolean updateStudentMajorGPAByEmail(String email,Double majorGPA){
        Query query = new Query(Criteria.where("address").is(email));
        Update update = new Update().set("majorGPA",majorGPA);

        UpdateResult result = mongoTemplate.updateFirst(query,update,Student.class);

        if(result != null){
            return true;
        }else{
            return false;
        }
    }

    public void deleteStudentByEmail(String email){
        Query query = new Query(Criteria.where("address").is(email));
        mongoTemplate.remove(query,Student.class);
    }

}