package com.TeamProject.Dao;

import com.TeamProject.Person.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class AdminDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //create
    public boolean addAdmin(Admin admin){
        if(findAdminByEmail(admin.getEmail()) == null) {
            mongoTemplate.save(admin);
            return true;
        }
        return false;
    }

    //find admin
    public Admin findAdminById (String id){
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Admin.class);
    }

    public Admin findAdminByEmail (String email){
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Admin.class);
    }

    //update admin by adminID
    public void updateById(Admin admin) {
        Query query = new Query(Criteria.where("id").is(admin.getAdminID()));
        Update update = new Update().set("name", admin.getName()).set("gender", admin.getGender());
        //update the first result
        mongoTemplate.updateFirst(query, update, Admin.class);
        //update all found results
        // mongoTemplate.updateMulti(query, update, Admin.class);
    }

    //update admin by email
    public void updateByEmail(Admin admin) {
        Query query = new Query(Criteria.where("email").is(admin.getEmail()));
        Update update = new Update().set("name", admin.getName()).set("gender", admin.getGender());
        //update the first result
        mongoTemplate.updateFirst(query, update, Admin.class);
        //update all found results
        // mongoTemplate.updateMulti(query, update, Admin.class);
    }

    //delete admin by adminID
    public void deleteAdminByEmail (String email){
        Query query = new Query(Criteria.where("email").is(email));
        mongoTemplate.remove(query, Admin.class);
    }

    //TODO
    public boolean addStudentApplications(String email, String gender, String dob, String pw, String major) {
        return true;
    }
}
