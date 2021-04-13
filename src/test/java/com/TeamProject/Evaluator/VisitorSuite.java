package com.TeamProject.Evaluator;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VisitorSuite {
    @DisplayName("Overall visitor test case of professor")
    @Test
    void OverallProf(){
        OverallVisitor evaluator = new OverallVisitor();

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);

        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
        CourseSection comp3004C = new CourseSection(comp3004, 'C', 2021, "Summer");

        Professor mia = new Professor();
        mia.addPassRates(comp3004A, 0.6);
        mia.addPassRates(comp3004B, 0.8);
        mia.addPassRates(comp3004C, 0.4);

        double expect = (0.6+0.8+0.4)/3.0;
        assertEquals(expect, mia.accept(evaluator));
    }

    @DisplayName("Major visitor test case of professor")
    @Test
    void MajorProf(){
        MajorVisitor evaluator = new MajorVisitor();

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
        CourseSection comp3004C = new CourseSection(comp3004, 'C', 2021, "Summer");

        Professor mia = new Professor();
        mia.addSection(comp3004A);
        mia.addSection(comp3004B);
        mia.addSection(comp3004C);

        mia.addPassRates(comp3004A, 0.6);
        mia.addPassRates(comp3004B, 0.8);
        mia.addPassRates(comp3004C, 0.4);

        double expect = 0.4;
        assertEquals(expect, mia.accept(evaluator));
    }

    @DisplayName("Overall visitor test case of student")
    @Test
    void OverallStu(){
        OverallVisitor evaluator = new OverallVisitor();

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
        CourseSection comp3004C = new CourseSection(comp3004, 'C', 2021, "Winter");
        CourseSection math2000A = new CourseSection(math2000, 'A', 2021, "Winter");

        Student mia = new Student();
        mia.setMajor("MATH");
        mia.addSection(comp3004A);
        mia.addSection(comp3004B);
        mia.addSection(comp3004C);
        mia.addSection(math2000A);
        mia.addFinalGrade(comp3004A,'C');
        mia.addFinalGrade(comp3004B,'A');
        mia.addFinalGrade(comp3004C,'B');
        mia.addFinalGrade(math2000A,'A');

        double expect = (4.0+4.0+3.0+2.0)/4.0;
        assertEquals(expect, mia.accept(evaluator));
    }

    @DisplayName("Major visitor test case of student")
    @Test
    void MajorStu(){
        MajorVisitor evaluator = new MajorVisitor();

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course math2000 = university.getDepartments().orderTheCourse("MATH",2000);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");
        CourseSection comp3004B = new CourseSection(comp3004, 'B', 2021, "Winter");
        CourseSection comp3004C = new CourseSection(comp3004, 'C', 2021, "Winter");
        CourseSection math2000A = new CourseSection(math2000, 'A', 2021, "Winter");

        Student mia = new Student();
        mia.setMajor("MATH");
        mia.addSection(comp3004A);
        mia.addSection(comp3004B);
        mia.addSection(comp3004C);
        mia.addSection(math2000A);
        mia.addFinalGrade(comp3004A,'C');
        mia.addFinalGrade(comp3004B,'A');
        mia.addFinalGrade(comp3004C,'B');
        mia.addFinalGrade(math2000A,'A');

        double expect = 4.0;
        assertEquals(expect, mia.accept(evaluator));
    }
}
