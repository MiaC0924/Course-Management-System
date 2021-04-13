package com.TeamProject;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.University;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.DataAnalyst.DataArray;
import com.TeamProject.DataAnalyst.DropCourseData;
import com.TeamProject.DataAnalyst.SampleDateStrategy;
import com.TeamProject.DataAnalyst.StrategyCenter;
import com.TeamProject.Person.Person;
import com.TeamProject.Person.Student;

import java.time.LocalDate;
import java.util.Date;

public class testMain {
    public static void main(String[] args) throws InterruptedException {

        Student s = new Student();
        s.setName("Gura");
        s.setGender("F");

        Student s1 = new Student();
        s1.setName("Ame");
        s1.setGender("F");

        Student s2 = new Student();
        s2.setName("Kevin");
        s2.setGender("M");

        System.out.println(((Person)s).toString());

        Date d = new Date();
        University university = new University("Carleton University");
        //Course object
        Course math2107 = university.getDepartments().orderTheCourse("MATH",2107);
        Course comp3004 = university.getDepartments().orderTheCourse("COMP",3004);
        Course comp3005 = university.getDepartments().orderTheCourse("COMP",3004);

        DropCourseData dc1 = new DropCourseData(s,d,math2107);
        DropCourseData dc2 = new DropCourseData(s1,d,comp3004);
        DropCourseData dc3 = new DropCourseData(s2 ,d ,comp3004);

        Thread.sleep(1000);

        Date d1 = new Date();
        DropCourseData dc4 = new DropCourseData(s,d1,comp3004);
        DropCourseData dc5 = new DropCourseData(s1,d1,comp3005);
        DropCourseData dc6 = new DropCourseData(s2 , d1,comp3005);

        DataArray da = new DataArray();
        da.add(dc1);
        da.add(dc2);
        da.add(dc3);
        da.add(dc4);
        da.add(dc5);
        da.add(dc6);

        System.out.println(da.toString());

        StrategyCenter strategyCenter = new StrategyCenter();

        DataArray da2 = new DataArray();

        da2 = strategyCenter.useSampleDateStrategy(da);
        System.out.println(da2.toString());
        da2 = strategyCenter.useSampleValueStrategy(da);
        System.out.println(da2.toString());
        da2 = strategyCenter.useSkipNStrategy(da);
        System.out.println(da2.toString());
        da2 = strategyCenter.useDateDecorator(da);
        System.out.println(da.toString());
        da2 = strategyCenter.useSkippedDateStrategy(da2);

    }

}
