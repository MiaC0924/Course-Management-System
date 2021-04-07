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
    public void addAdmin(Admin admin){
        if(findAdminById(admin.getAdminID()) == null) {
            mongoTemplate.save(admin);
        }
    }

    //find admin by adminID
    public Admin findAdminById (String id){
        Query adminQuery = new Query(Criteria.where("adminId").is(id));
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
    public void deleteAdminById (int id){
        Query adminQuery=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(adminQuery, Admin.class);
    }

}
