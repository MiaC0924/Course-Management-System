package com.TeamProject.DBSeeder;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.University;
import com.TeamProject.Person.*;
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

        ProfessorApplication professorApplication = new ProfessorApplication(
                "Cat",
                "Female",
                "cat@fack.com",
                LocalDate.now(),
                "cat",
                "ECON"
                );
        adm.addProfessorApp(professorApplication);

        StudentApplication studentApplication = new StudentApplication(
                "Bird",
                "Male",
                "bird@fack.com",
                LocalDate.now(),
                "bird",
                "ECON"
        );
        adm.addStudentApp(studentApplication);

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


        /**Professor*/
        Professor jp = new Professor(
                "JP",
                "Male",
                "jp@fack.com",
                LocalDate.now(),
                    "Professor",
                "COMP"
        );


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


        /**Student*/
        Student mia = new Student(
                "Mia",
                "Female",
                "mia@stu.com",
                LocalDate.now(),
                "student",
                "COMP"
        );

        Student kevin = new Student(
                "Kevin",
                "Male",
                "kevin@stu.com",
                LocalDate.now(),
                "student",
                "MATH"
        );

        studentRepository.deleteAll();

        comp3004A.attachObserver(mia);
        math2000A.attachObserver(mia);
        comp3004A.attachObserver(jp);
        math2000A.attachObserver(jp);
        //comp3004A.setFinalgrades(mia.getStudentNumber(),'A');
        jp.addPassRates(comp3004A, 0.6);

        ArrayList<CourseSection> courseSectionList = new ArrayList<>();
        courseSectionList.add(comp3004A);
        courseSectionList.add(comp3004B);
        courseSectionList.add(math2000A);

        ArrayList<Student> stuList = new ArrayList<>();
        stuList.add(mia);
        stuList.add(kevin);
        professorRepository.saveAll(profList);
        studentRepository.saveAll(stuList);
        courseSectionRepository.saveAll(courseSectionList);
    }
}
