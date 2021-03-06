package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.*;
import com.TeamProject.Person.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseSectionDao courseSectionDao;

    //create student
    public boolean createStudent() {
//        System.out.println(adminDao.findAdminById("101"));
        HashMap<Integer,StudentApplication> map = new HashMap<>();
        map = adminDao.findAdminById("101").getStudentAppList();
        if (map.isEmpty()) {
            return false;
        } else {
            //from the hash map{email:[name,gender,dob,pw,major]} create students
            for (Map.Entry<Integer, StudentApplication> entry : map.entrySet()) {
                int applicationId = entry.getKey();
                String email = entry.getValue().getEmail();
                String name = entry.getValue().getName();
                String gender = entry.getValue().getGender();
                LocalDate dob = entry.getValue().getBirthday();
                String major = entry.getValue().getMajor();
                String pw = entry.getValue().getPassword();
                Student student = new Student(name, gender, email, dob, pw, major);
                studentDao.addStudent(student);
                adminDao.deleteStudentApplications(applicationId);
            }
            return true;
        }
    }
        //create professor
        public boolean createProfessor() {
            HashMap<Integer,ProfessorApplication> map = new HashMap<>();
            map = adminDao.findAdminById("101").getProfAppList();
            if (map.isEmpty()) {
                return false;
            } else {
                //from the hash map{email:[name,gender,dob,pw,major]} create students
                for (Map.Entry<Integer, ProfessorApplication> entry : map.entrySet()) {
                    int applicationId = entry.getKey();
                    String email = entry.getValue().getEmail();
                    String name = entry.getValue().getName();
                    String gender = entry.getValue().getGender();
                    LocalDate dob = entry.getValue().getBirthday();
                    String major = entry.getValue().getMajor();
                    String pw = entry.getValue().getPassword();
                    Professor professor = new Professor(name, gender, email, dob, pw, major);
                    professorDao.addProfessor(professor);
                    adminDao.deleteProfessorApplications(applicationId);
                }
                return true;
            }
        }

        // create course
        public boolean createCourse (String major,int code, Character section, int year, String season){
            Course course = courseDao.findCourseByCourseCode(major,code);
            System.out.println(course);
            if(course==null){
                CourseBuilding cb = new Department();
                Course c = cb.orderTheCourse(major,code);
                courseDao.addCourse(c);
                CourseSection cs = new CourseSection(c,section,year,season);
                Professor pro = professorDao.findProfByMajor(major);
                courseSectionDao.addSection(cs);
                courseSectionDao.updateProfessorBySectionId(cs.getSectionID(), pro);
                professorDao.addCourseSectionByProfId(pro.getProfID(),cs);
                return true;
            }
//            if the course is in the database then fail
            CourseSection courseSection = courseSectionDao.findSectionByInfo(section,year,season);
            if(courseSection == null){
//                find specfic major prof
                CourseSection newCourseSection = new CourseSection(course,section,year,season);
                Professor pro = professorDao.findProfByMajor(major);
                courseSectionDao.addSection(newCourseSection);
                courseSectionDao.updateProfessorBySectionId(newCourseSection.getSectionID(), pro);
                professorDao.addCourseSectionByProfId(pro.getProfID(),newCourseSection);
                return true;
            }
            else {
                return false;
            }
        }
        //delete student
        public boolean deleteStudent (int id){
            //check if the student exist
            if (studentDao.findStudentByStuId(id) != null) {
                //check if the student have any courses
                if(studentDao.findStudentByStuId(id).getTerms().isEmpty()){
                    studentDao.deleteStudentByStuId(id);
                    return true;
                }
                else{
                    return false;
                }
            }
            return false;
        }

        //delete prof
        public boolean deleteProfessor (int id){
            //check if the prof exist
            if (professorDao.findProfById(id) != null) {
                //check if the prof have any courses
                if(professorDao.findProfById(id).getTerms().isEmpty()){
                    professorDao.deleteProfById(id);
                    return true;
                }
                else{
                    return false;
                }
            }
            return false;
        }

        //delete course
        public boolean deleteCourse (int id,String majorcode, int code,Character section){
            //from created course list
            //if registration start: deregister stu and prof then delete
            CourseSection courseSection = courseSectionDao.findSectionById(id);
            System.out.println(courseSection);
            if(courseSection != null){
                String season = courseSection.getTermSeason();
                int year = courseSection.getTermYear();
                Term term = new Term(year,season);
                if(validRegisterPeriod(term)){
                    //deregister student and
                    for (int i = 0; i < courseSection.getStudentList().size(); i++){
                        studentDao.deleteCourseSectionByStuId(courseSection.getStudentList().get(i).getStudentNumber(),courseSection);
                    }
                    //deregister prof
                    professorDao.deleteCourseSectionByProfId(courseSection.getProfessor().getProfID(),courseSection);
                }
                //delete course
                courseSectionDao.deleteSection(id);
                return true;
            }
            return false;

        }

        public boolean validRegisterPeriod (Term t){
            if (t.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
                if (t.getSeason() == "Fall") {
                    return true;
                } else if (t.getSeason() == "Summer") {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        public CourseSection findCourseSectionById(int id){
            return courseSectionDao.findSectionById(id);
        }

}

