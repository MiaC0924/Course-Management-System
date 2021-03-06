package com.TeamProject.Service;

import com.TeamProject.Course.Course;
import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Deliverable;
import com.TeamProject.Dao.*;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.ProfessorApplication;
import com.TeamProject.Person.Student;
import com.TeamProject.Person.StudentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private CourseSectionDao courseSectionDao;
    @Autowired
    private CourseDao courseDao;

    public boolean applyforCreation(String email,String name,String gender,String dob,String pw,String major){
//        System.out.println(dob);
        //put key and value into hashmap in admin database check if application exist
        LocalDate DOB = LocalDate.parse(dob);
//        System.out.println(DOB);
        //check if the application list is empty
        HashMap<Integer, ProfessorApplication> map = new HashMap<>();
//        System.out.println(adminDao.findAdminById("101"));
        map = adminDao.findAdminById("101").getProfAppList();
//        System.out.println(map);
        if(map.isEmpty()){
//            System.out.println("map is null");
            int applicationId = adminDao.addProfosserApplications(name,gender,email,DOB,pw,major);
            return true;
        }
        else{
            //check if the application is exist already
//            System.out.println("map is not null");
            for(Map.Entry<Integer, ProfessorApplication> set : map.entrySet()){
                if(set.getValue().getEmail() == email){
                    return false;
                }
            }
            adminDao.addProfosserApplications(name,gender,email,DOB,pw,major);
            return true;
        }
    }

    public boolean createDeliver(String major , int code, Character section,int year,String season, String deliver,LocalDate DL){
//        Course c = courseDao.findCourseByCourseCode(major,code);
        CourseSection cs = courseSectionDao.findSectionByAllInfo(major ,  code,  section, year, season);
        if(cs == null){
            System.out.println("cs no found");
            return false;
        }else{
//            System.out.println(cs);
            boolean added = cs.addDeliverable(deliver, DL);
            courseSectionDao.addSection(cs);
            return added;
        }
    }

    public void modifyDeliver(){}

    public void deleteDeliver(){}

    public void submitListOfGrades(String major,int code,String section,String stuId,int grade){

    }

    public boolean submitListOfFinalGrades(int profId, int courseSectionId, ArrayList<Integer> stuIds, ArrayList<Character> finalGrades){
        //check if prof have this course

        for(int i=0;i<professorDao.findProfById(profId).getTerms().size();i++){
            for(int j=0;j<professorDao.findProfById(profId).getTerms().get(i).getCourseSections().size();j++){
                if(professorDao.findProfById(profId).getTerms().get(i).getCourseSections().get(j)==courseSectionId){
                    for(int k=0;k<stuIds.size();k++){
                        //studentDao.setFinalGrade(stu,courseSectionId,grade);

                       // courseSectionDao.setFinalGrade(courseSectionId,studentDao.findStudentByStuId(stuIds.get(k)),finalGrades.get(k));

                        return true;
                    }

                }

            }
        }
        return false;
    }

    public boolean submitFinalGradeForOne(String email,CourseSection courseSection,int stuId,Character grade){
//        System.out.println("in service cs is "+courseSection);
        if(courseSection==null){return false;}
        boolean added = courseSectionDao.setFinalGrade(courseSection.getSectionID(),stuId,grade);
        courseSectionDao.addSection(courseSection);

        return added;

    }

    public Professor findProfessorByEmail(String email){
        return professorDao.findProfByEmail(email);
    }

    public ArrayList<Deliverable> findAllDeliverable(){
        return null;
    }

    public boolean removeDeliverable(CourseSection cs,String name){
        CourseSection courseSection =courseSectionDao.findSectionById(cs.getSectionID());
        if(cs == null){
            System.out.println("cs no found");
            return false;
        }else{
//            System.out.println(cs);
            boolean added = courseSection.removeDeliverable(name);
            courseSectionDao.addSection(cs);
            return added;
        }

    }
}
