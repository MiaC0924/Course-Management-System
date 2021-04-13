package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.University;
import com.TeamProject.Person.Admin;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DBSeeder implements CommandLineRunner {
    private AdminRepository adminRepository;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private CourseSectionRepository courseSectionRepository;

    public DBSeeder(AdminRepository ar, ProfessorRepository pr, StudentRepository sr,
                    CourseRepository cr, CourseSectionRepository csr){
        this.adminRepository = ar;
        this.professorRepository = pr;
        this.studentRepository = sr;
        this.courseRepository = cr;
        this.courseSectionRepository = csr;
    }

    @Override
    public void run(String... args) throws Exception {
        /**Admin*/
        Admin adm = new Admin(
                "Admin",
                "Female",
                "admin@fack.com",
                LocalDate.now(),
                "Admin"
        );

        Admin john = new Admin(
                "John",
                "Male",
                "alexx@fack.com",
                LocalDate.now(),
                "Admin"
        );

        adminRepository.deleteAll();

        ArrayList<Admin> adminList = new ArrayList<>();
        adminList.add(adm);
        adminList.add(john);
        adminRepository.saveAll(adminList);

        /**Course*/
        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);

        courseRepository.deleteAll();

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(comp3004);
        courseList.add(math2000);
        courseRepository.saveAll(courseList);

        /**CourseSection*/
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
        CourseSection math2000A = new CourseSection(math2000, 'A', 2021, "Winter");

        courseSectionRepository.deleteAll();

        ArrayList<CourseSection> courseSectionList = new ArrayList<>();
        courseSectionList.add(comp3004A);
        courseSectionList.add(comp3004B);
        courseSectionList.add(math2000A);
        courseSectionRepository.saveAll(courseSectionList);

        /**Professor*/
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

        /**Student*/
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
