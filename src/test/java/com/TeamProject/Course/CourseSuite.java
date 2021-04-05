package com.TeamProject.Course;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class CourseSuite {
    @DisplayName("Course test case")
    @Test
    void Course() {
        //Course comp3004A = new Course("COMP", 3004);
        //Course comp3004B = new Course("COMP", 3004);
        University university = new University("Carleton University");

        //Course object
        Course math2107 = university.getDepartments().orderTheCourse("MATH",2107);
        Course comp3004A = university.getDepartments().orderTheCourse("COMP",3004);
        Course comp3004B = university.getDepartments().orderTheCourse("COMP",3004);
        Course math1007 = university.getDepartments().orderTheCourse("MATH",1007);


        assertEquals("COMP3004", comp3004A.getCourseName());
        assertEquals(true, comp3004A.match(comp3004B));
        assertEquals(false, comp3004A.match(math2107));
        assertEquals("MATH", math2107.getMajor());
        assertEquals(2107, math2107.getCode());

       // comp3004A.addPreCondition(math2000);
        ArrayList<Course> expect = new ArrayList<>();
        expect.add(math1007);

        System.out.println(expect.get(0).getCourseName());
        math2107.getPreCondition().coursePre(math2107.getCode()).get(0).getCourseName();
        //assertEquals(expect, math2107.getPreCondition().coursePre(math2107.getCode()));

    }

    @DisplayName("Section test case")
    @Test
    void Section() {
        Student mia = new Student();
        Professor john = new Professor();
        University university = new University("Carleton");


        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        //Course comp3004 = new Course("COMP", 3004);
        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);

        comp3004A.addStudent(mia);
        comp3004A.addGrade('A');

        ArrayList<Student> expectStudent = new ArrayList<>();
        ArrayList<Character> expectGrade = new ArrayList<>();
        expectStudent.add(mia);
        expectGrade.add('A');

        assertEquals(john, comp3004A.getProfessor());
        assertEquals(100001, comp3004A.getSectionID());
        assertEquals("Winter2021", comp3004A.getTerm().toString());
        assertEquals("COMP3004A", comp3004A.getSectionName());
        assertEquals(expectGrade, comp3004A.getGradeList());
        assertEquals(expectStudent, comp3004A.getStudentList());
    }
}
