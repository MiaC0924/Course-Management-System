package com.TeamProject.Dao;

import com.TeamProject.Course.CourseSection;
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

    public void addStudent(Student stu){
        mongoTemplate.save(stu);
    }

    //find
    public Student findStudentByStuId(int id){
        Query query = new Query((Criteria.where("studentNumber").is(id)));
        return mongoTemplate.findOne(query, Student.class);
    }

    public Student findStudentByEmail(int email){
        Query query = new Query((Criteria.where("email").is(email)));
        return mongoTemplate.findOne(query, Student.class);
    }

    public boolean updateStudentMajorGPAByStuId(int id,Double majorGPA){
        Student stu = findStudentByStuId(id);
        if(stu != null) {
            Query query = new Query(Criteria.where("studentNumber").is(id));
            Update update = new Update().set("majorGPA", majorGPA);
            mongoTemplate.updateFirst(query, update, Student.class);
            return true;
        }
        return false;
    }

    public void addStudentCourseSectionByStuId(int id, CourseSection cs){
        Student stu = findStudentByStuId(id);
        stu.addSection(cs);
        mongoTemplate.save(stu);
    }




    public void deleteStudentByStuId(int id){
        Query query = new Query(Criteria.where("studentNumber").is(id));
        mongoTemplate.remove(query,Student.class);
    }

    //TODO
    public void addCourseByStuId(int id, CourseSection courseSection) {
    }

    //TODO
    public void deleteCourseByStuId(int id, CourseSection courseSection) {
    }
}