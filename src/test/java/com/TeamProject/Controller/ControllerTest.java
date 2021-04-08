package com.TeamProject.Controller;

import com.TeamProject.Observer.Observer;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.Admin;
import com.TeamProject.Course.*;
import com.TeamProject.config.StudentLoginHandlerInterceptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControllerTest {
    University university = new University("Carleton University");
    LocalDate BOD = LocalDate.of(2000, 1, 1);
    Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
    Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
    @DisplayName("User case 12 test")
    @Test
    public void UserCase12() {

        /* Student attempts to register a course
         * Actor: Student, Admin staff
         * Precondition: The student has log in, and the system is operational
         * */
        Student s1 = new Student("Allen", "Male", "XXX,XXX,XXX", BOD, "","Computer Science");
        RegisterController c = new RegisterController();

        Term winter2021 = new Term(2021, "Winter");
        CourseSection comp3004A = new CourseSection(comp3004, 'A', winter2021);
        CourseSection comp3004B = new CourseSection(comp3004, 'B', winter2021);//Pre: Course is create in list.

        Term fall2021 = new Term(2021, "Fall");
        CourseSection comp3005A = new CourseSection(comp3005, 'A', fall2021);
        CourseSection comp3005B = new CourseSection(comp3005, 'B', fall2021);

        //The student selects ‘course registration’ action
        //The student selects a course from the created courses list and submit registration

        //checking period is valid
        boolean period = c.validRegisterPeriod(comp3005B.getTerm());
        assertEquals(true,period);

        //checking register successful;
        boolean success  = c.register(s1,comp3005B);
        assertEquals(true,success);

        //checking correct added-in course;
        ArrayList<Observer> expect1 = new ArrayList<Observer>();
        expect1.add(s1);

        assertEquals(expect1, comp3005B.getObservers());//should be only one observer;

        //Student select a course comp3004A in Course list.
        period = c.validRegisterPeriod(comp3004A.getTerm());
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
        /* Student attempts to drop a course
         * Actor: Student, Admin staff
         * Precondition: The student has log in, and the system is operational
         * */
        Student s1 = new Student("Allen", "Male", "XXX,XXX,XXX", BOD, "", "Computer Science");
        RegisterController c = new RegisterController();
        DropController dc = new DropController();
        Term fall2020 = new Term(2020, "Fall");//It is valid to drop
        CourseSection comp3005C = new CourseSection(comp3005, 'C', fall2020);
        Term fall2021 = new Term(2021, "Fall");//It is valid to drop
        CourseSection comp3005A = new CourseSection(comp3005, 'A', fall2021);
        CourseSection comp3005B = new CourseSection(comp3005, 'B', fall2021);
        Term Summer2021 = new Term(2021, "Summer");//It is valid to drop
        CourseSection comp3004A = new CourseSection(comp3004, 'A', Summer2021);
        CourseSection comp3004B = new CourseSection(comp3004, 'B', Summer2021);
        //Student login
        //The student selects ‘drop a course’ action
        //The student selects a course from his/her registered courses list and submit dropping
        //The system reports there are no registered courses to select
        boolean drop = dc.dropCourse(s1,comp3005B);
        assertEquals(false, drop);

        //there is some course list out;
        c.register(s1,comp3005A);
        c.register(s1,comp3005B);
        c.register(s1,comp3004A);
        c.register(s1,comp3004B);
        s1.addCourse(comp3005C);//Force added course
        comp3005C.attachObserver(s1);//Force added course
        //Student select a course comp3005B in his register list.

        //The system processes the dropping with reimbursement and no WDN.
        int valid = dc.validDropPeriod(comp3004A.getTerm());
        assertEquals(0, valid);

        drop = dc.dropCourse(s1,comp3004A);
        assertEquals(true, drop);

        //The system processes the dropping with WDN
        valid = dc.validDropPeriod(comp3005A.getTerm());
        assertEquals(1, valid);

        drop = dc.dropCourse(s1,comp3005A);
        assertEquals(true, drop);

        // The system reports that now is out of any dropping period
        valid = dc.validDropPeriod(comp3005C.getTerm());
        assertEquals(-1, valid);

        drop = dc.dropCourse(s1,comp3005C);
        assertEquals(false, drop);

        // The system confirms the success of dropping
        // The admin staff denies the dropping
    }
    @DisplayName("User case 14 test")
    @Test
    public void UserCase14(){
        /* Title: Student attempts to submit a course deliverable
         * Actor: Student
         * Precondition: The student has log in, and the system is operational
         * */
        Student s1 = new Student("Allen", "Male", "XXX,XXX,XXX", BOD, "", "Computer Science");
        //The student selects a course from his/her list of registered courses
        // The system shows there is no registered course

        //
        //The student selects a deliverable from the list of available deliverables for that course

        //The system shows there is no available deliverable

        //The student uploads a file/files and submits it/them


        // The system confirms the success of submission

        /*
         * Postcondition: Student has submitted a course deliverable
         *
         * Rule: A deliverable is available only if its submission deadline has not passed,
         *       and professor created but did not delete it
         *
         * Rule: A course would be shown on the courses list of a student
         *       if the course was created but not deleted,
         *       and that student registered to these courses but did not dropped it
         * */

    }
    @DisplayName("User case 15 test")
    @Test
    public void UserCase15(){
        /* Title: Professor create a course deliverable
         * Actor: Professor
         * Precondition: The professor has log in, and the system is operational
         * */
        University university = new University("Carleton University");
        LocalDate BOD = LocalDate.of(2000, 1, 1);
        Professor p1 = new Professor("Allen", "Male", "XXX,XXX,XXX", BOD, "", "Computer Science");
        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3005);
        Term fall2021 = new Term(2021, "Fall");
        CourseSection comp3005A = new CourseSection(comp3005, 'A', fall2021);
        CourseSection comp3005B = new CourseSection(comp3005, 'B', fall2021);
        CourseSection comp3005C = new CourseSection(comp3005, 'C', fall2021);

        //The professor selects a course from his/her courses list
        // The system shows there is no course assigned to this professor


        //The professor selects ‘create a course deliverable’ action

        //The professor enters deliverable description and deadline

        //The system shows the course was not in this term or finished


        // The system confirms the success of creation



        /*
         * Postcondition: Professor has created a course deliverable
         *
         * Rule: The ‘create a course deliverable’ action could be performed once
         *       professor select the course from his/her list
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted,
         *       and that professor was assigned to these courses
         * */

    }
    @DisplayName("User case 16 test")
    @Test
    public void UserCase16(){
        /* Title: Professor modify a course deliverable
         * Actor: Professor
         * Precondition: The professor has log in, and the system is operational
         * */

        //The professor selects a course from his/her courses list
        //The system shows there is no course assigned to this professor
        //The professor selects a deliverable from the list of deliverables for that course
        //The system shows there is no deliverable

        //The professor selects ‘modify the course deliverable’ action
        // The professor modifies the deliverable description or deadline
        //The system confirms the success of deletion


        /*
         * Postcondition: Professor has modified a course deliverable
         *
         * Rule: The ‘modify a course deliverable’ action could be performed once
         *       professor selects the course deliverable from the deliverable list of a course
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted, and that professor was assigned to these courses
         *
         * Rule: A deliverable would be shown on the deliverables list of a course
         *       if the deliverable was created but did not deleted
         * */
    }
    @DisplayName("User case 17 test")
    @Test
    public void UserCase17(){
        /* Title: Professor deletes a course deliverable
         * Actor: Professor
         * Precondition: The professor has log in, and the system is operational
         * */

        //The professor selects a course from his/her courses list
        //The system shows there is no course assigned to this professor
        //The professor selects a deliverable from the list of deliverables for that course
        //The system shows there is no deliverable

        //The professor selects ‘delete the course deliverable’ action
        // The professor deletes the deliverable description or deadline
        //The system confirms the success of deletion

        /*
         * Postcondition: Professor has deleted a course deliverable
         *
         * Rule: The ‘delete  a course deliverable’ action could be performed once
         *       professor selects the course deliverable from the deliverable list of a course
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted, and that professor was assigned to these courses
         *
         * Rule: A deliverable would be shown on the deliverables list of a course
         *       if the deliverable was created but did not deleted
         * */
    }
    @DisplayName("User case 18 test")
    @Test
    public void UserCase18(){
        /* Title: Professor submits a list of grades for a course deliverable
         * Actor: Professor
         * Precondition: The Professor has log in, and the system is operational
         * */

        //The professor selects a course from his/her courses list
        //The system shows there is no course assigned to this professor
        //The professor selects a deliverable from the list of deliverables for that course
        //The system shows there is no deliverable

        //The professor selects ‘submit a list of deliverable grades’ action
        //The professor enters a list of grades
        //The system reports the grades was submitted
        //The system confirms the success of submission

        /*
         * Postcondition: Professor has submitted a list of grades for the course deliverable
         *
         * Rule: The ‘submit   a course deliverable’ action could be performed once
         *       professor selects the course deliverable from the deliverable list of a course
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted, and that professor was assigned to these courses
         *
         * Rule: A deliverable would be shown on the deliverables list of a course
         *       if the deliverable was created but did not deleted
         * */

    }
    @DisplayName("User case 19 test")
    @Test
    public void UserCase19(){
        /* Title: Professor submits a list of final grades for a course
         * Actor: Professor
         * Precondition: The Professor has log in, and the system is operational
         * */
        //professor login
        //profeesor select a course from his list
        //no course assigned
        //select submit final
        //enters a list of grade
        //grades was submitted
        //confirm success of submission.

        /*
         * Postcondition: Professor has submitted a list of grades for the course deliverable
         *
         * Rule: The ‘submit a list of final grades’ action could be performed once
         *       professor selects the course deliverable from the deliverable list of a course
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted, and that professor was assigned to these courses
         *
         * Rule: If the course was graded, then later submission will override the earlier submission
         * */

    }
    @DisplayName("User case 20 test")
    @Test
    public void UserCase20(){
        /* Title: Professor submits grade of a student
         * Actor: Professor
         * Precondition: The Professor has log in, and the system is operational
         * */

        //professor login
        //professor select a course
        //The system shows there is no course assigned to this professor
        //The professor selects a student from the list of students for that course
        //The system shows there is no student
        //The professor selects a deliverable or ‘final grade’ from the grading list
        //The system shows there is no available selection for grading
        //The professor enters a grade
        //The system confirms the success of submission

        /*
         * Postcondition: Professor has submitted a list of grades for the course deliverable
         *
         * Rule: The ‘submit a list of deliverable grades’ action could be performed once
         *       professor selects the course deliverable from the deliverable list of a course
         *
         * Rule: A course would be shown on the courses list of a professor
         *       if the course was created but not deleted, and that professor was assigned to these courses
         *
         *Rule: A deliverable would be shown on the grading list
         *       if the deliverable was created but did not deleted

         * Rule: If the course was graded, then later submission will override the earlier submission
         * */





    }
}

