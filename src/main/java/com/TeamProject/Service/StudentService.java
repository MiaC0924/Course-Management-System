package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.StudentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CourseSectionDao courseSectionDao;

    public Student findStuByEmail(String email){
        return studentDao.findStudentByEmail(email);
    }
    public ArrayList<CourseSection> getAllCoursebyStu(String stu){
        courseSectionDao.getAllCourseByStu(studentDao.findStudentByEmail(stu));
        return null;
    }

    public boolean applyForCreation(String email,String name,String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        LocalDate DOB = LocalDate.parse(dob);
        //check if the application list is empty
        if(adminDao.findAdminById("101").getStudentAppList().isEmpty()){
            adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
        else{
            //check if the application is exist already
            for(Map.Entry<String, StudentApplication> set : adminDao.findAdminById("101").getStudentAppList().entrySet()){
                if(set.getKey() == email){
                    return false;
                }
            }
            adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
    }

    public ArrayList<CourseSection> getAllCourseByStu (Student stu){
        Student s=studentDao.findStudentByStuId(stu.getStudentNumber());
        ArrayList<Integer> sectionIds = new ArrayList<>();
        ArrayList<CourseSection> courseSections = new ArrayList<>();
        for (int i=0;i<stu.getTerms().size();i++){
            for(int j=0;j<stu.getTerms().get(i).getSectionIds().size();j++){
                sectionIds.add(stu.getTerms().get(i).getSectionIds().get(j));
            }
        }
        for (int i=0;i<sectionIds.size();i++){
            courseSections.add(courseSectionDao.findSectionById(sectionIds.get(i)));
        }
        return courseSections;
    }

    public boolean registerCourse(int id,int year,String season, Character section,String majorcode,int code){
        Student stu = studentDao.findStudentByStuId(id);
        if(validRegisterPeriod(year,season)){
            CourseBuilding department = new Department();
            Course course = department.orderTheCourse(majorcode,code);
            CourseSection courseSection = new CourseSection(course,section,year,season);
            //courseSection.attachObserver(stu);
            studentDao.addCourseSectionByStuId(id,courseSection);
            return true;
        }
        else{
            return false;
        }

    }

    public boolean dropCourse(int id,int year,String season, Character section,String majorcode,int code){
        Student stu = studentDao.findStudentByStuId(id);
        if(validRegisterPeriod(year,season)){
            CourseBuilding department = new Department();
            Course course = department.orderTheCourse(majorcode,code);
            CourseSection courseSection = new CourseSection(course,section,year,season);
            //courseSection.attachObserver(stu);
            studentDao.deleteCourseSectionByStuId(id,courseSection);
            return true;
        }
        else{
            return false;
        }
    }

    public void submitDeliver(){


    }

    public boolean validRegisterPeriod(int tY , String tS){
        if(tY == Calendar.getInstance().get(Calendar.YEAR)){
            if(tS =="Fall"){
                return true;
            }else if(tS =="Summer"){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
