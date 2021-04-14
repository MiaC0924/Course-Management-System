package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.AdminDao;
import com.TeamProject.Dao.CourseSectionDao;
import com.TeamProject.Dao.StudentDao;
import com.TeamProject.Observer.Observer;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.StudentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
    public ArrayList<CourseSection> getAllCourse(){
        return courseSectionDao.getAllCourse();
    }

    public boolean applyForCreation(String email,String name,String gender,String dob,String pw,String major){
        //put key and value into hashmap in admin database check if application exist
        LocalDate DOB = LocalDate.parse(dob);
        //check if the application list is empty
        HashMap<Integer,StudentApplication> map = new HashMap<>();
        map = adminDao.findAdminById("101").getStudentAppList();
        if(map.isEmpty()){
            int applicationId = adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
        else{
            //check if the application is exist already
            for(Map.Entry<Integer, StudentApplication> set : map.entrySet()){
                if(set.getValue().getEmail() == email){
                    return false;
                }
            }
            adminDao.addStudentApplications(name,gender,email,DOB,pw,major);
            return true;
        }
    }

    public ArrayList<CourseSection> getAllCourseByStu (String email){
        ArrayList<CourseSection> s = new ArrayList<>();
        ArrayList<CourseSection> c = courseSectionDao.getAllCourse();
        for (int i =0;i<c.size();i++){
            System.out.println(c.get(i).getStudentList());
            for(int j=0;j<c.get(i).getStudentList().size();j++){
                System.out.println(email);
                System.out.println(c.get(i).getStudentList().get(j).getEmail());
                if(c.get(i).getStudentList().get(j).getEmail() .equals(email)){
                    s.add(c.get(i));
                }
            }
        }
        return s;
    }


    public boolean registerCourse(int id,int year,String season, Character section,String majorcode,int code){
        Student stu = studentDao.findStudentByStuId(id);
        System.out.println(stu);
        CourseSection cs = courseSectionDao.findSectionByAllInfo(majorcode,code,section,year,season);
        System.out.println(cs);

//         if(cs.getObservers().contains(stu)){
//             return false;
//         }
//         if(validRegisterPeriod(year,season)){
//             System.out.println(cs);
//             cs.attachObserver(stu);
//             courseSectionDao.addSection(cs);
//             studentDao.addStudent(stu);

        for(Student s:cs.getStudentList()){
            System.out.println(s);
            if (s.getStudentNumber()==stu.getStudentNumber()){
                return false;
            }
        }
        if(validRegisterPeriod(year,season)){
            System.out.println(cs.getObservers());
            cs.attachObserver(stu);
            courseSectionDao.addStudentBySectionId(cs.getSectionID(),stu);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean dropCourse(int id,int year,String season, Character section,String majorcode,int code){
        Student stu = studentDao.findStudentByStuId(id);
        CourseSection cs = courseSectionDao.findSectionByAllInfo(majorcode,code,section,year,season);
        System.out.println(cs);
        ArrayList<CourseSection> csList = new ArrayList<CourseSection>();
        csList = getAllCourseByStu(stu.getEmail());
        //System.out.println(stu.containCourse(cs));
        ArrayList<Integer> containId = new ArrayList<>();
        ArrayList<Integer> containSectionId = new ArrayList<>();
        if(cs==null){
            return false;
        }
        for(int i=0;i<cs.getStudentList().size();i++){
            containId.add(cs.getStudentList().get(i).getStudentNumber());
        }
        for(int i=0;i<csList.size();i++){
            containSectionId.add(csList.get(i).getSectionID());
        }
        boolean contain = containId.contains(id);

        if(cs.getStudentList().isEmpty()){return false;}
        if(contain==false){
            return false;
        }


        if(validRegisterPeriod(year,season)){
            System.out.println("first student list"+cs.getStudentList());
            System.out.println(stu);
            System.out.println(cs.getStudentList().contains(stu));
            cs.detachObserver(stu);
            courseSectionDao.deleteStudentBySectionId(cs.getSectionID(),stu);
            System.out.println("second student list"+cs.getStudentList());
            System.out.println(cs.getStudentList().contains(stu));
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
            if(tS .equals("Fall")){
                return true;
            }else if(tS .equals("Summer")){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
}
