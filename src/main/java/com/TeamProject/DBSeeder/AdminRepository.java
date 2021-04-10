package com.TeamProject.DBSeeder;

import com.TeamProject.Person.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
//    List<Admin>  findByID(String id);
}