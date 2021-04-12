package com.TeamProject.Dao;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.MathCourse;
import com.TeamProject.Person.Admin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class CourseDao {
    @Autowired
    MongoTemplate mongoTemplate;

    private static final Log log = LogFactory.getLog(CourseDao.class);


    public boolean addCourse(Course course){
        mongoTemplate.save(course , "Courses");
        return false;
    }

    public Course findAdminByNameAndCode (String name, int code){
        Query query = new Query(Criteria.where("name").is(name).and("code").is(code));
        return mongoTemplate.findOne(query, Course.class,"Courses");
    }

    public Course  findCourseByCourseCode(String course,int code){
        Query query = new Query(Criteria.where("name").is(course).and("code").is(code));
        return mongoTemplate.findOne(query, Course.class,"Courses");
    }


    public void deleteCourseByNameAndCode(String name, int code){
        Query query=new Query(Criteria.where("name").is(name).and("code").is(code));
        mongoTemplate.remove(query, Course.class, "Courses");
    }
}
