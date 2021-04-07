package com.TeamProject.Person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

public class PersonSuite {
    @DisplayName("Student test case")
    @Test
    void Student() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Student mia = new Student("Mia","Female","123 Ottawa St", BOD, "","MATH");
        assertEquals(100000001, mia.getStudentNumber());
        assertEquals(BOD, mia.getBirthDay());

        Student john = new Student();
        assertEquals(100000002, john.getStudentNumber());

        john.setMajor("COMP");
        assertEquals("COMP", john.getMajor());
    }

    @DisplayName("Professor test case")
    @Test
    void Professor() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Professor mia = new Professor("Mia","Female","123 Ottawa St", BOD, "","MATH");
        assertEquals(9001,mia.getProfID());
        assertEquals(BOD, mia.getBirthDay());
        assertEquals("MATH", mia.getFaculty());

        Professor john = new Professor();
        assertEquals(9002, john.getProfID());

        john.setGender("Male");
        assertEquals("Male", john.getGender());
    }

    @DisplayName("Admin test case")
    @Test
    void Admin() {
        LocalDate BOD = LocalDate.of(2000, 1, 1);

        Admin mia = new Admin("Mia","Female","mia@fack.com", BOD, "");
        assertEquals(101,mia.getAdminID());
        assertEquals("mia@fack.com", mia.getEmail());

        Admin john = new Admin();
        assertEquals(102, john.getAdminID());

        john.setName("John");
        assertEquals("John", john.getName());
        assertNotNull(john.getEmail());
        assertEquals("", john.getEmail());
    }
}
