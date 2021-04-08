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
        if(findStudentByStuId(stu.getStudentNumber())!= null){
            return false;
        }
        mongoTemplate.save(stu);
        return true;
    }
    public Student findStudentByStuId(int id){
        Query query = new Query((Criteria.where("studentNumber").is(id)));
        Student stu = mongoTemplate.findOne(query , Student.class);
        return stu;
    }

    public boolean updateStudentMajorGPAByStuId(int id,Double majorGPA){
        Query query = new Query(Criteria.where("studentNumber").is(id));
        Update update = new Update().set("majorGPA",majorGPA);

        UpdateResult result = mongoTemplate.updateFirst(query,update,Student.class);

        if(result != null){
            return true;
        }else{
            return false;
        }
    }

    public void deleteStudentByStuId(int id){
        Query query = new Query(Criteria.where("studentNumber").is(id));
        mongoTemplate.remove(query,Student.class);
    }

}