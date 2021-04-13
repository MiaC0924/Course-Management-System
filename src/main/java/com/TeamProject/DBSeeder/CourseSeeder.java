package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.University;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component()
public class CourseSeeder implements CommandLineRunner {
    private CourseRepository courseRepository;

    public CourseSeeder(CourseRepository courseRepository){this.courseRepository = courseRepository; }

    @Override
    public void run(String... args) throws Exception {
        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);

        courseRepository.deleteAll();

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(comp3004);
        courseList.add(math2000);
        courseRepository.saveAll(courseList);
    }
}
