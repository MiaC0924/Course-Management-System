package com.TeamProject.Person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class PersonSuite {
    @DisplayName("Student test case")
    @Test
    void Student() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Student Mia = new Student("Mia","Female","123 Ottawa St",BOD,"MATH");
        assertEquals(100000001,Mia.getStudentNumber());
        assertEquals(BOD, Mia.getBirthDay());

        Student John = new Student();
        assertEquals(100000002,John.getStudentNumber());

        John.setMajor("COMP");
        assertEquals("COMP",John.getMajor());
    }

    @DisplayName("Professor test case")
    @Test
    void Professor() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Professor Mia = new Professor("Mia","Female","123 Ottawa St",BOD,"MATH");
        assertEquals(9001,Mia.getProfID());
        assertEquals(BOD, Mia.getBirthDay());
        assertEquals("MATH",Mia.getFaculty());

        Professor John = new Professor();
        assertEquals(9002,John.getProfID());

        John.setGender("Male");
        assertEquals("Male",John.getGender());
    }

    @DisplayName("Admin test case")
    @Test
    void Admin() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Admin Mia = new Admin("Mia","Female","123 Ottawa St",BOD);
        assertEquals(101,Mia.getAdminID());
        assertEquals("123 Ottawa St", Mia.getAddress());

        Admin John = new Admin();
        assertEquals(102,John.getAdminID());

        John.setName("John");
        assertEquals("John",John.getName());
    }
}
