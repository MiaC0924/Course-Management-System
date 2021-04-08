package com.TeamProject;

import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Student;

import java.time.LocalDate;

public class testMain {
    public static void main(String[] args) {
        StudentDao studentdao = new StudentDao();
        Student student1 = new Student("Gura","F","1234@qq.com",LocalDate.now(),"MATH");

        studentdao.addStudent(student1);



    }

}
