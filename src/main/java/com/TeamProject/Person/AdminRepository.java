package com.TeamProject.Person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
//    List<Admin>  findByNameEquals(String name);
//    List<Admin>  findByID(String id);
}