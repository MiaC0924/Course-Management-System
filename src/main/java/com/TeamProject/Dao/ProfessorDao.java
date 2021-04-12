package com.TeamProject.Dao;


import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class ProfessorDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //create
    public boolean addProfessor(Professor prof){
        if(findProfByEmail(prof.getEmail()) == null){
            mongoTemplate.save(prof);
            return true;
        }
        return false;
    }

    //find prof
    public Professor findProfById (int id){
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Professor.class);
    }

    public Professor findProfByEmail (String email){
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Professor.class);
    }

    //update
    public void updatePersonalInfoById(Professor prof) {
        Query query = new Query(Criteria.where("id").is(prof.getProfID()));
        Update update = new Update().set("name", prof.getName()).set("gender", prof.getGender());
        //update the first result
        mongoTemplate.updateFirst(query, update, Professor.class);
        //update all found results
        // mongoTemplate.updateMulti(query, update, prof.class);
    }

    public void updatePersonalInfoByEmail(Professor prof) {
        Query query = new Query(Criteria.where("email").is(prof.getEmail()));
        Update update = new Update().set("name", prof.getName()).set("gender", prof.getGender());
        //update the first result
        mongoTemplate.updateFirst(query, update, Professor.class);
        //update all found results
        // mongoTemplate.updateMulti(query, update, prof.class);
    }


    public void updateFacultyById(int id, String inputFaculty){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("faculty", inputFaculty);
        mongoTemplate.updateFirst(query, update, Professor.class);
    }

    public void addCourseSectionByProfId(int id, CourseSection inputCS){
        Professor prof = findProfById(id);
        prof.addSection(inputCS);
        mongoTemplate.save(prof);
    }

    public void deleteCourseSectionByProfId(int id, CourseSection inputCS){
        Professor prof = findProfById(id);
        prof.removeSection(inputCS);
        mongoTemplate.save(prof);
    }

    //delete
    public void deleteProfByEmail (String email){
        Query query = new Query(Criteria.where("email").is(email));
        mongoTemplate.remove(query, Professor.class);
    }


    //pass rate
//    public double getOverallPassRateById (int id){
//        Query query = new Query(Criteria.where("id").is(id));
//        Professor prof = mongoTemplate.findOne(query, Professor.class);
//        return prof.getPassRateOverAll();
//    }
//
//    public double getCurrentTermPassRateById (int id){
//        Query query = new Query(Criteria.where("id").is(id));
//        Professor prof = mongoTemplate.findOne(query, Professor.class);
//        return prof.getPassRateOfCurr();
//    }

}
