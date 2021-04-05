package com.TeamProject.Observer;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.Admin;
import com.TeamProject.Course.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObserverTest {
    @DisplayName("Observer test")
    @Test
    void ObserverAttachTest(){
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Professor p1 = new Professor();
        Admin a = new Admin();
        Term t = new Term(2020,"Fall");
        Course c = new Course("MATH",1001);
        CourseSection mathA = new CourseSection(c,'A',t);
        CourseSection mathB = new CourseSection(c,'B',t);
        CourseSection mathC = new CourseSection(c,'C',t);

        //Simple attach event
        mathA.attachObserver(s1);
        mathB.attachObserver(s1);
        mathB.attachObserver(s2);
        mathA.attachObserver(p1);
        mathA.attachObserver(a);

        ArrayList<CourseSection> expects1 = new ArrayList<CourseSection>();
        ArrayList<CourseSection> expects2 = new ArrayList<CourseSection>();
        ArrayList<CourseSection> expects3 = new ArrayList<CourseSection>();

        ArrayList<Observer> expectOs1 = new ArrayList<Observer>();
        ArrayList<Observer> expectOs2 = new ArrayList<Observer>();
        ArrayList<Observer> expectOs3 = new ArrayList<Observer>();

        expects1.add(mathA);
        expects1.add(mathB);//[mathA,mathB]
        expects2.add(mathB);//[mathB]
        //expects3 = [];
        expectOs1.add(s1);
        expectOs1.add(p1);
        expectOs1.add(a);//[s1,p1,a]
        expectOs2.add(s1);
        expectOs2.add(s2);//[s2,s1]
        //expectOs3 = [];

        //check course correct store into observer;
        assertEquals(expects1,s1.getCourses());//several added-in
        assertEquals(expects2,s2.getCourses());//single added
        assertEquals(expects3,s3.getCourses());//empty check

        //check observer correct store into subject
        assertEquals(expectOs1,mathA.getObservers());//several added-in
        assertEquals(expectOs2,mathB.getObservers());//single added
        assertEquals(expectOs3,mathC.getObservers());//empty check

        //Duplicate attach event;
        mathA.attachObserver(s1);
        mathA.attachObserver(s1);

        //check duplicate course added-in;
        assertEquals(expects1,s1.getCourses());

        //check duplicate observer added-in
        assertEquals(expectOs1,mathA.getObservers());//duplicate added-in no change.
    }

    void ObserverDetachTest(){
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Professor p1 = new Professor();
        Admin a = new Admin();
        Term t = new Term(2020,"Fall");
        Course c = new Course("MATH",1001);
        CourseSection mathA = new CourseSection(c,'A',t);
        CourseSection mathB = new CourseSection(c,'B',t);
        CourseSection mathC = new CourseSection(c,'C',t);

        //Simple attach event
        mathA.attachObserver(s1);
        mathB.attachObserver(s2);
        mathA.attachObserver(p1);
        mathA.attachObserver(a);

        ArrayList<CourseSection> expects1 = new ArrayList<CourseSection>();
        ArrayList<CourseSection> expects2 = new ArrayList<CourseSection>();

        ArrayList<Observer> expectOs1 = new ArrayList<Observer>();
        ArrayList<Observer> expectOs2 = new ArrayList<Observer>();

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
        assertEquals(expects1,s1.getCourses());
        assertEquals(expects2,s2.getCourses());
        assertEquals(expectOs1,mathA.getObservers());
        assertEquals(expectOs2,mathB.getObservers());

        //Duplicate attach event;
        mathA.detachObserver(s1);
        mathA.detachObserver(s1);

        assertEquals(expects1,s1.getCourses());
        assertEquals(expects2,s2.getCourses());
        assertEquals(expectOs1,mathA.getObservers());
        assertEquals(expectOs2,mathB.getObservers());
    }
}
