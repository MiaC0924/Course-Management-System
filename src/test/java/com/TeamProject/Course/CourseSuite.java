package com.TeamProject.Course;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals("COMP3004", comp3004A.getCourseName());
        assertTrue(comp3004A.match(comp3004B));
        assertFalse(comp3004A.match(math2107));
        assertEquals("MATH", math2107.getMajor());
        assertEquals(2107, math2107.getCode());

        assertEquals("tutorial C1", comp3004A.tutorial.toString());
        assertEquals("tutorial M1", math2107.tutorial.toString());
    }

    @DisplayName("CourseSection test case")
    @Test
    void CourseSection() {
        Student mia = new Student();
        University university = new University("Carleton");

        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");

        comp3004A.addStudent(mia);
        comp3004A.addGrade(mia,'A');

        ArrayList<Student> expectStudent = new ArrayList<>();
        ArrayList<Character> expectGrade = new ArrayList<>();
        expectStudent.add(mia);
        expectGrade.add('A');

        assertEquals(100001, comp3004A.getSectionID());
        assertTrue(comp3004A.sameTerm(winter2021));
        assertEquals("COMP3004A", comp3004A.getSectionName());
        assertEquals(expectGrade.get(0), comp3004A.getGradeList().get(mia));
        assertEquals(expectStudent, comp3004A.getStudentList());
    }
}
