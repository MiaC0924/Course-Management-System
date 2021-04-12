package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Person.Professor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class ProfessorSeeder implements CommandLineRunner {

    private ProfessorRepository professorRepository;

    public ProfessorSeeder(ProfessorRepository professorRepository){this.professorRepository = professorRepository; }

    @Override
    public void run(String... args) throws Exception {
        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection math2000A = new CourseSection(math2000, 'A', 2021, "Winter");

        Professor jp = new Professor(
                "JP",
                "Male",
                "jp@fack.com",
                LocalDate.now(),
                "Professor",
                "COMP"
        );

        jp.addSection(comp3004A);
        jp.addSection(math2000A);
        jp.addPassRates(comp3004A, 0.6);

        Professor matthew = new Professor(
                "Matthew",
                "Female",
                "matthew@fack.com",
                LocalDate.now(),
                "Professor",
                "MATH"
        );

        professorRepository.deleteAll();

        ArrayList<Professor> profList = new ArrayList<>();
        profList.add(jp);
        profList.add(matthew);
        professorRepository.saveAll(profList);
    }
}
