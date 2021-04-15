package com.TeamProject;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Course.University;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Service.ProfessorService;
import com.TeamProject.Service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
public class UseCaseTest {

    @DisplayName("Course test case")
    @Test
    void SubmitGrade() {
        StudentService studentService;
        ProfessorService professorService;

        Professor jp = new Professor();
        Student mia = new Student();
        University university = new University("Carleton");

        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        CourseSection comp3004A = new CourseSection(comp3004, 'A', 2021, "Winter");

        comp3004A.addStudent(mia);
        comp3004A.setProfessor(jp);
        System.out.println(jp.getTerms());
        for(int i=0;i<jp.getTerms().size();i++){
            for(int j=0;j<jp.getTerms().get(i).getCourseSections().size();j++){
                if(jp.getTerms().get(i).getCourseSections().get(j)==comp3004A.getSectionID()){
                    //check if the student is in this course section
                    for(int k=0;k<comp3004A.getStudentList().size();k++){
                        if(comp3004A.getStudentList().get(k).getStudentNumber()==mia.getStudentNumber()){
                            // studentDao.setFinalGrade(stu,courseSectionId,grade);

                            if(comp3004A != null) {
                                //comp3004A.setGrade(mia, 'A');
                                System.out.println(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(false);




    }


}
