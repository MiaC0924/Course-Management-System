package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.University;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component()
public class CourseSectionSeeder implements CommandLineRunner {

    private CourseSectionRepository courseSectionRepository;

    public CourseSectionSeeder(CourseSectionRepository courseSectionRepository){this.courseSectionRepository = courseSectionRepository; }

    @Override
    public void run(String... args) throws Exception {
        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");

        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);
        CourseSection math2000A = new CourseSection(math2000, 'A', 2021, "Winter");

        courseSectionRepository.deleteAll();

        ArrayList<CourseSection> courseSectionList = new ArrayList<>();
        courseSectionList.add(comp3004A);
        courseSectionList.add(comp3004B);
        courseSectionList.add(math2000A);
        courseSectionRepository.saveAll(courseSectionList);
    }
}
