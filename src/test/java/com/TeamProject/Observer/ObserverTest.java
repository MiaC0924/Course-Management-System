package com.TeamProject.Observer;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.Admin;
import com.TeamProject.Course.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    @DisplayName("Observer test")
    @Test
    void ObserverAttachTest(){
        University university = new University("Carleton University");
        Student s1 = new Student();
        Student s2 = new Student();
        Professor p1 = new Professor();
        Admin a = new Admin();
        Course math = university.getDepartments().orderTheCourse("MATH",2004);
        CourseSection mathA = new CourseSection(math,'A',2020,"Fall");
        CourseSection mathB = new CourseSection(math,'B',2020,"Fall");
        CourseSection mathC = new CourseSection(math,'C',2020,"Fall");

        //Simple attach event - add students into course sections
        mathA.attachObserver(s1);
        mathB.attachObserver(s1);
        mathB.attachObserver(s2);
        mathA.attachObserver(p1);
        mathA.attachObserver(a);

        ArrayList<CourseSection> expects1 = new ArrayList<>();
        ArrayList<CourseSection> expects2 = new ArrayList<>();

        ArrayList<Observer> expectOs1 = new ArrayList<>();
        ArrayList<Observer> expectOs2 = new ArrayList<>();
        ArrayList<Observer> expectOs3 = new ArrayList<>();

        expects1.add(mathA);
        expects1.add(mathB);//[mathA,mathB]
        expects2.add(mathB);//[mathB]
        expectOs1.add(s1);
        expectOs1.add(p1);
        expectOs1.add(a);//[s1,p1,a]
        expectOs2.add(s1);
        expectOs2.add(s2);//[s2,s1]

        //check course correct store into observer;
        assertTrue(s1.containCourse(mathA));
        assertTrue(s1.containCourse(mathB));//several added-in
        assertTrue(s2.containCourse(mathB));//single added

        //check observer correct store into subject
        assertEquals(expectOs1,mathA.getObservers());//several added-in
        assertEquals(expectOs2,mathB.getObservers());//single added
        assertEquals(expectOs3,mathC.getObservers());//empty check

        //Duplicate attach event;
        mathA.attachObserver(s1);
        mathA.attachObserver(s1);

        //check duplicate observer added-in
        assertEquals(expectOs1,mathA.getObservers());//duplicate added-in no change.
    }

    void ObserverDetachTest(){
        University university = new University("Carleton University");
        Student s1 = new Student();
        Student s2 = new Student();
        Professor p1 = new Professor();
        Admin a = new Admin();

        Course math = university.getDepartments().orderTheCourse("MATH",3004);
        CourseSection mathA = new CourseSection(math,'A',2020,"Fall");
        CourseSection mathB = new CourseSection(math,'B',2020,"Fall");

        //Simple attach event
        mathA.attachObserver(s1);
        mathB.attachObserver(s2);
        mathA.attachObserver(p1);
        mathA.attachObserver(a);

        ArrayList<CourseSection> expects1 = new ArrayList<>();
        ArrayList<CourseSection> expects2 = new ArrayList<>();

        ArrayList<Observer> expectOs1 = new ArrayList<>();
        ArrayList<Observer> expectOs2 = new ArrayList<>();

        expects1.add(mathA);
        expects1.add(mathB);//["mathA","mathB"]
        expects2.add(mathB);//["mathB"]
        expectOs1.add(s1);
        expectOs1.add(p1);
        expectOs1.add(a);//[s1,p1,a]
        expectOs2.add(s2);
        expectOs2.add(s1);//[s2,s1]

        mathA.detachObserver(s1);
        mathB.detachObserver(s2);

        expects1.remove(mathA);//["mathB"]
        expects2.remove(mathB);//[]

        expectOs1.remove(p1);
        expectOs2.remove(s2);//[s2]

        //Simple detach event;
        assertFalse(s1.containCourse(mathA));
        assertFalse(s2.containCourse(mathB));
        assertEquals(expectOs1,mathA.getObservers());
        assertEquals(expectOs2,mathB.getObservers());

        //Duplicate attach event;
        mathA.detachObserver(s1);
        mathA.detachObserver(s1);

        assertEquals(expectOs1,mathA.getObservers());
        assertEquals(expectOs2,mathB.getObservers());
    }
}
