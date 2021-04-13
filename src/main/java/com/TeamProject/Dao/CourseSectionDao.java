package com.TeamProject.Dao;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseSectionDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //create
    public boolean addSection(CourseSection section){
        if(findSectionById(section.getSectionID()) == null){
            mongoTemplate.save(section,"Sections");
            System.out.println("added success");
            return true;
        }
        return false;
    }

    //create
    public void deleteSection(int secID){
        Query query = new Query(Criteria.where("sectionID").is(secID));
        mongoTemplate.remove(query, CourseSection.class,"Sections");
    }

    //find
    public CourseSection findSectionById(int id){
        Query query = new Query(Criteria.where("sectionID").is(id));
        return mongoTemplate.findOne(query, CourseSection.class,"Sections");
    }

    public ArrayList<CourseSection> getAllCourseByStu (Student stu){

        ArrayList<CourseSection> css = getAllCourse();
        for(CourseSection cs:css){
            if(!cs.getObservers().contains(stu)){
                css.remove(cs);
            }
        }
        return css;
    }

    public ArrayList<CourseSection> getAllCourse (){
        int count=100001;
        ArrayList<CourseSection> css = new ArrayList<CourseSection>();
        while(count<100100){
            CourseSection cs =findSectionById(count);
            if(cs==null){
//                System.out.println("No found at "+count);
                count++;
            }else{
                count++;
                css.add(cs);
            }
        }
        System.out.println(css);
        return css;
    }
//
    public CourseSection findSectionByInfo(Character section,int year,String season){
        Query query = new Query();
        query.addCriteria(Criteria.where("section").is(section).and("cYear").is(year).and("cSeason").is(season));
        CourseSection c= mongoTemplate.findOne(query,CourseSection.class,"Sections");
        System.out.println(c);
        return c;
    }



    //update
    public boolean updateProfessorBySectionId(int id, Professor professor){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.setProfessor(professor);
            mongoTemplate.save(section,"Sections");
            return true;
        }
        return false;
    }

    public boolean addStudentBySectionId(int id, Student stu){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.addStudent(stu);
            mongoTemplate.save(section,"Sections");
            return true;
        }
        return false;
    }

    public boolean setGradeBySectionId(int id, Student stu, Character grade){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.setGrade(stu, grade);
            mongoTemplate.save(section,"Sections");
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
