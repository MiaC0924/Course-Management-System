package com.TeamProject.Service;

import com.TeamProject.Course.*;
import com.TeamProject.Dao.*;
import com.TeamProject.Person.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

@Service
public class CourseSectionService {
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

    public ArrayList<CourseSection> getAllCourse(){
        return courseSectionDao.getAllCourse();
    }

    public ArrayList<CourseSection> getAllCourseByProf(Professor prof){
        return courseSectionDao.getAllCourseByProf(prof);
    }

    public CourseSection getCourseById(int id){
        return courseSectionDao.findSectionById(id);
    }

    public CourseSection findCourseSection(String major,int code,Character section,int year,String season){
        return courseSectionDao.findSectionByAllInfo(major,code,section,year,season);
    }

    public void updateCourse(CourseSection cs){
        courseSectionDao.updateSection(cs);
    }

    public ArrayList<Student> getStudentbyCourse(CourseSection cs){ return courseSectionDao.getStudentbyCourse(cs);}





}
