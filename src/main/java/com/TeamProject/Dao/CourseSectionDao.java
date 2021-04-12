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
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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

    //create
    public void deleteSection(int secID){
        Query query = new Query(Criteria.where("sectionID").is(secID));
        mongoTemplate.remove(query, CourseSection.class);
    }

    //find
    public CourseSection findSectionById(int id){
        Query query = new Query(Criteria.where("sectionID").is(id));
        return mongoTemplate.findOne(query, CourseSection.class);
    }

    public CourseSection findSectionByInfo(String course, int code, Character section,int year,String season){
        Query query = new Query();
        System.out.println("query");
        query.addCriteria(Criteria.where("course").is(course));
        System.out.println("course found");
        if(query == null){
            System.out.println("find null");
            return null;
        }
        List<CourseSection> courseSections = mongoTemplate.find(query,CourseSection.class);
        ArrayList<CourseSection> courseArray = new ArrayList<>();
        ArrayList<CourseSection> courseArray1 = new ArrayList<>();
        ArrayList<CourseSection> courseArray2= new ArrayList<>();
        ArrayList<CourseSection> courseArray3 = new ArrayList<>();

        for(CourseSection c : courseSections){
            if(c.getCode() == code){
                courseArray.add(c);
                System.out.println("added");
            }
            if(courseSections.isEmpty()){
                System.out.println("empty");
                return null;
            }
        }
        for(CourseSection c : courseArray){
            if(c.getSection() != section){
               courseArray1.add(c);
            }
            if(courseArray.isEmpty()){
                return null;
            }
        }
        for(CourseSection c : courseArray1){
            if(c.getTermYear() != year){
                courseArray2.add(c);
            }
            if(courseArray1.isEmpty()){
                return null;
            }
        }
        for(CourseSection c : courseArray2){
            if(c.getTermSeason() != season){
                courseArray3.add(c);
            }
            if(courseArray2.isEmpty()){
                return null;
            }
        }
        return courseArray.get(0);
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

//    public boolean addDeliverableBySectionId(int id, LocalDate date){
//        CourseSection section = findSectionById(id);
//        if(section != null) {
//            section.addDeliverable(date);
//            mongoTemplate.save(section);
//            return true;
//        }
//        return false;
//    }
}
