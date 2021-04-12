package com.TeamProject.Dao;

import com.TeamProject.Person.Admin;
import com.TeamProject.Person.ProfessorApplication;
import com.TeamProject.Person.StudentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    //delete admin
    public void deleteAdminByEmail (String email){
        Query query = new Query(Criteria.where("email").is(email));
        mongoTemplate.remove(query, Admin.class);
    }

    public void deleteAdminById (String id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Admin.class);
    }

    public boolean addStudentApplications(String name, String gender, String email,
                                          LocalDate dob, String pw, String major) {
        Admin admin = findAdminById("101");
        StudentApplication stuApp = new StudentApplication(name, gender, email, dob, pw, major);
        boolean added = admin.addStudentApp(stuApp);
        mongoTemplate.save(admin);
        return added;
    }

    public boolean addProfosserApplications(String name, String gender, String email,
                                          LocalDate dob, String pw, String major) {
        Admin admin = findAdminById("101");
        ProfessorApplication proApp = new ProfessorApplication(name, gender, email, dob, pw, major);
        boolean added = admin.addProfessorApp(proApp);
        mongoTemplate.save(admin);
        return added;
    }

    public boolean deleteStudentApplications(String studentEmail) {
        Admin admin = findAdminById("101");
        boolean deleted = admin.deleteStudentApp(studentEmail);
        mongoTemplate.save(admin);
        return deleted;
    }

    public boolean deleteProfessorApplications(String proEmail) {
        Admin admin = findAdminById("101");
        boolean deleted = admin.deleteProfessorApp(proEmail);
        mongoTemplate.save(admin);
        return deleted;
    }
}
