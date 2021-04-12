package com.TeamProject.Dao;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CourseSectionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //create
    public boolean addSection(CourseSection section){
        if(findSectionById(section.getSectionID()) == null){
            mongoTemplate.save(section);
            return true;
        }
        return false;
    }

    //find
    public CourseSection findSectionById(int id){
        Query query = new Query(Criteria.where("sectionID").is(id));
        return mongoTemplate.findOne(query, CourseSection.class);
    }

    //update
    public boolean updateProfessorBySectionId(int id, Professor professor){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.setProfessor(professor);
            mongoTemplate.save(section);
            return true;
        }
        return false;
    }

    public boolean addStudentBySectionId(int id, Student stu){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.addStudent(stu);
            mongoTemplate.save(section);
            return true;
        }
        return false;
    }

    public boolean setGradeBySectionId(int id, Student stu, Character grade){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.setGrade(stu, grade);
            mongoTemplate.save(section);
            return true;
        }
        return false;
    }

    public boolean addDeliverableBySectionId(int id, LocalDate date){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.addDeliverable(date);
            mongoTemplate.save(section);
            return true;
        }
        return false;
    }
}
