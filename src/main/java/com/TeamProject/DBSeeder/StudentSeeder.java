package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class StudentSeeder implements CommandLineRunner {

    private StudentRepository studentRepository;

    public StudentSeeder(StudentRepository studentRepository){this.studentRepository = studentRepository; }



    @Override
    public void run(String... args) throws Exception {

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);
        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);
        CourseSection math2000A = new CourseSection(math2000, 'A', winter2021);

        Student mia = new Student(
                "Mia",
                "Female",
                "mia@stu.com",
                LocalDate.now(),
                "student",
                "COMP"
        );

        mia.addSection(comp3004A);
        mia.addSection(math2000A);

        Student kevin = new Student(
                "Kevin",
                "Male",
                "kevin@stu.com",
                LocalDate.now(),
                "student",
                "MATH"
        );

        studentRepository.deleteAll();

        ArrayList<Student> stuList = new ArrayList<>();
        stuList.add(mia);
        stuList.add(kevin);
        studentRepository.saveAll(stuList);

    }
}
