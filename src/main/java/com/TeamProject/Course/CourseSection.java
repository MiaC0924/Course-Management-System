package com.TeamProject.Course;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import java.util.ArrayList;

public class CourseSection{
    private static int count  = 100001;
    private int sectionID;

    private Course course;
    private Character section;
    private Term term;

    private int room;
    private String building;

    private Professor professor;
    private ArrayList<Student> studentList;
    private ArrayList<Character>  gradeList;

    public CourseSection(Course inputCourse, Character inputSection, Term inputTerm){
        sectionID = count;
        ++count;

        course = inputCourse;
        section = inputSection;
        term = inputTerm;

        room = 0;
        building = "";

        professor = new Professor();
        studentList = new ArrayList<>();
        gradeList = new ArrayList<>();
    }

    //getters
    public int getSectionID()      { return sectionID; }
    public String getSectionName() { return course.getCourseName() + section; }
    public String getTerm()        { return term.toString(); }

    public int    getRoom()        { return room;     }
    public String getBuilding()    { return building; }

    public Professor getProfessor()            { return professor;   }
    public ArrayList<Student> getStudentList() { return studentList; }
    public ArrayList<Character> getGradeList() { return gradeList;   }

    //setters
    public void setBuilding(String inputBuilding) { building = inputBuilding;  }
    public void setRoom(int inputRoom)            { room = inputRoom;          }
    public void setProfessor(Professor inputProf) { professor = inputProf;     }
    public void addStudent(Student inputStu)      { studentList.add(inputStu); }
    public void addGrade(Character inputGrade)    { gradeList.add(inputGrade); }
}
