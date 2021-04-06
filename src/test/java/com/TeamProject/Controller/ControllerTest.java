package com.TeamProject.Controller;

import com.TeamProject.Observer.Observer;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.Admin;
import com.TeamProject.Course.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ControllerTest {
    @DisplayName("User case 12 test")
    @Test
    public void UserCase12() {
        University university = new University("Carleton University");
        Controller c =new Controller();
        LocalDate BOD = LocalDate.of(2000, 1, 1);
        Student s1 = new Student("Allen", "Male", "XXX,XXX,XXX", BOD, "Computer Science");

        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);
        CourseSection comp3004B = new CourseSection(comp3004, 'B', winter2021);
        CourseSection comp3004C = new CourseSection(comp3004, 'C', winter2021);//Pre: Course is create in list.

        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        Term fall2021 = new Term(2021, "Fall");
        CourseSection comp3005A = new CourseSection(comp3005, 'A', fall2021);
        CourseSection comp3005B = new CourseSection(comp3005, 'B', fall2021);
        CourseSection comp3005C = new CourseSection(comp3005, 'C', fall2021);

        //Student login
        //Student Click register button
        //Student select a course comp3004B in the Course list.
        //checking period is valid
        boolean period = c.validPeriod(comp3005B.getTerm());
        assertEquals(true,period);

        //checking register successful;
        boolean success  = c.register(s1,comp3005B);
        assertEquals(true,success);

        //checking correct added-in course;
        ArrayList<Observer> expect1 = new ArrayList<Observer>();
        expect1.add(s1);

        assertEquals(expect1, comp3005B.getObservers());//should be only one observer;

        //Student select a course comp3004A in Course list.
        period = c.validPeriod(comp3004A.getTerm());
        assertEquals(false,period);//this step is with-in the register;

        //checking register successful;
        success = c.register(s1,comp3004A);
        assertEquals(false,success);

        //checking not added-in course
        ArrayList<Observer> expectEmpty = new ArrayList<Observer>();
        assertEquals(expectEmpty, comp3004A.getObservers());

        //Admin denied student register the course;
        Admin a = new Admin();
        c.AdminDenied(a,s1,comp3005B);

        assertEquals(expectEmpty, comp3005B.getObservers());

    }
    @DisplayName("User case 13 test")
    @Test
    public void UserCase13(){
        University university = new University("Carleton University");
        Controller c =new Controller();
        LocalDate BOD = LocalDate.of(2000, 1, 1);
        Student s1 = new Student("Allen", "Male", "XXX,XXX,XXX", BOD, "Computer Science");

        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        Term fall2021 = new Term(2021, "Fall");
        CourseSection comp3005A = new CourseSection(comp3005, 'A', fall2021);
        CourseSection comp3005B = new CourseSection(comp3005, 'B', fall2021);
        CourseSection comp3005C = new CourseSection(comp3005, 'C', fall2021);



        //Student login
        //Student Click drop the course button
        //there is no course to select;


        //there is some course list out;
        c.register(s1,comp3005A);
        c.register(s1,comp3005B);
        c.register(s1,comp3005C);
        //Student select a course comp3004B in his register list.
        //checking period is valid


    }
    @DisplayName("User case 14 test")
    @Test
    public void UserCase14(){

    }
    @DisplayName("User case 15 test")
    @Test
    public void UserCase15(){

    }
    @DisplayName("User case 16 test")
    @Test
    public void UserCase16(){

    }
    @DisplayName("User case 17 test")
    @Test
    public void UserCase17(){

    }
    @DisplayName("User case 18 test")
    @Test
    public void UserCase18(){

    }
    @DisplayName("User case 19 test")
    @Test
    public void UserCase19(){

    }
    @DisplayName("User case 20 test")
    @Test
    public void UserCase20(){

    }
}
