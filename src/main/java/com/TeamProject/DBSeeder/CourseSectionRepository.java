package com.TeamProject.DBSeeder;

import com.TeamProject.Course.CourseSection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSectionRepository extends MongoRepository<CourseSection, String> {
}
