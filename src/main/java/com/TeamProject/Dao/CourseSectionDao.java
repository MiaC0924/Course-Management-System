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
            mongoTemplate.save(section);
            System.out.println("added success");
            return true;
        }else{
            mongoTemplate.save(section);
            return false;
        }
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

    public ArrayList<CourseSection> getAllCourseByStu (Student stu){
        ArrayList<CourseSection> css = getAllCourse();
        System.out.println(css);
        for(CourseSection cs:css){
            if(!cs.getObservers().contains(stu)){
                css.remove(cs);
            }
        }
        return css;
    }

    public ArrayList<CourseSection> getAllCourseByProf(Professor prof){
        ArrayList<CourseSection> css = getAllCourse();
        ArrayList<CourseSection> c = new ArrayList<CourseSection>();

        for(CourseSection cs:css){
            System.out.println(cs.getSectionID());
            if(cs.getProfessor()==null){
                System.out.println("No Prof");
                continue;
            }else{
                System.out.println(cs.getProfessor().getName());
                System.out.println(prof.getName());
            }
            if(cs.getProfessor().getName().equals(prof.getName())){
                c.add(cs);
            }
        }
        System.out.println(c);
        return c;
    }

    public ArrayList<CourseSection> getAllCourseByCourseSection (Course co,Character ch){
        ArrayList<CourseSection> c = new ArrayList<>();

        ArrayList<CourseSection> s = getAllCourse();
        for (int i =0;i<s.size();i++){
            if(s.get(i).getSectionName().equals(co
                    .getCourseName())&&s.get(i).getSection()==ch){
                c.add(s.get(i));
            }
        }
        return c;
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
        return css;
    }
//
    public CourseSection findSectionByInfo(Character section,int year,String season){
        Query query = new Query();
        query.addCriteria(Criteria.where("section").is(section).and("cYear").is(year).and("cSeason").is(season));
        CourseSection c= mongoTemplate.findOne(query,CourseSection.class);
        System.out.println(c);
        return c;
    }

    public CourseSection findSectionByAllInfo(String major,int code, Character section,int year,String season){
        Query query = new Query();
        query.addCriteria(Criteria.where("section").is(section).and("cYear").is(year).and("cSeason").is(season));
        List<CourseSection> cl= mongoTemplate.find(query,CourseSection.class);
        for(CourseSection c:cl){
            if(c.getSectionName().equals(""+major+code+section)){
                return c;
            }
        }
//        System.out.println("null");
        return null;
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

    public boolean deleteStudentBySectionId(int id, Student stu){
        CourseSection section = findSectionById(id);
        if(section != null) {
            section.removeStudent(stu);
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
