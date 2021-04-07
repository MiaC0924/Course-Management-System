package com.TeamProject.Dao;

import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Student;
import com.mongodb.client.result.UpdateResult;
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

    //find admin by adminID
    public Admin findAdminById (String id){
        Query adminQuery = new Query(Criteria.where("adminId").is(id));
        return mongoTemplate.findOne(adminQuery, Admin.class);
    }

    public Admin findAdminByEmail (String email){
        Query adminQuery = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(adminQuery, Admin.class);
    }

    //update admin by adminID
    public void updateTest(Admin admin) {
        Query adminQuery = new Query(Criteria.where("id").is(admin.getAdminID()));
        Update update = new Update().set("name", admin.getName()).set("address", admin.getEmail());
        //update the first result
        mongoTemplate.updateFirst(adminQuery, update, Admin.class);
        //update all found results
        // mongoTemplate.updateMulti(adminQuery, update, Admin.class);
    }

    //delete admin by adminID
    public void deleteAdminByEmail (String email){
        Query adminQuery=new Query(Criteria.where("email").is(email));
        mongoTemplate.remove(adminQuery, Admin.class);
    }

}
