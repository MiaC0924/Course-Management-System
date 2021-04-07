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

        Professor mia = new Professor();
        mia.addPassRates(0.6);
        mia.addPassRates(0.8);
        mia.addPassRates(0.75);

        double expect = (0.6+0.8+0.75)/3.0;
        assertEquals(expect, mia.accept(evaluator));
    }

    @DisplayName("Overall visitor test case of student")
    @Test
    void OverallStu(){
        OverallVisitor evaluator = new OverallVisitor();

        University university = new University("Carleton University");
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);
        CourseSection comp3004B = new CourseSection(comp3004, 'B', winter2021);
        CourseSection comp3004C = new CourseSection(comp3004, 'B', winter2021);
        Student mia = new Student();
        mia.addFinalGrade(comp3004A,'C');
        mia.addFinalGrade(comp3004B,'A');
        mia.addFinalGrade(comp3004C,'B');

        double expect = (4.0+3.0+2.0)/3.0;
        assertEquals(expect, mia.accept(evaluator));
    }
}
