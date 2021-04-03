package com.TeamProject.Evaluator;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

        Student mia = new Student();
        mia.addFinalGrade('C');
        mia.addFinalGrade('A');
        mia.addFinalGrade('B');

        double expect = (4.0+3.0+2.0)/3.0;
        assertEquals(expect, mia.accept(evaluator));
    }
}
