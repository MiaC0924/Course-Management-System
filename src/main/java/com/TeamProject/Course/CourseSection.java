package com.TeamProject.Course;

import com.TeamProject.Observer.Observer;
import com.TeamProject.Observer.Subject;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import java.util.ArrayList;

public class CourseSection implements Subject {
    private static int count  = 100001;
    private int sectionID;

    private Course course;
    private Character section;
    private Term term;

    private int room;
    private String building;

    private ArrayList<Observer> observers;
    private ArrayList<Character>  gradeList;
    private int state;

    public CourseSection(Course inputCourse, Character inputSection, Term inputTerm){
        sectionID = count;
        ++count;
        state = 0;

        course = inputCourse;
        section = inputSection;
        term = inputTerm;

        room = 0;
        building = "";

        observers = new ArrayList<Observer>();
        gradeList = new ArrayList<>();
    }

    //getters
    public int getSectionID()      { return sectionID; }
    public String getSectionName() { return course.getCourseName() + section; }
    public String getTerm()        { return term.toString(); }

    public int    getRoom()        { return room;     }
    public String getBuilding()    { return building; }
    public ArrayList<Observer> getObservers() { return observers; }

    public Professor getProfessor()            {
        Professor professor = null;
        for(Observer o:observers){
            if(o instanceof Professor){
                professor=(Professor) o;
            }
        }
        return professor;
    }

    public ArrayList<Student> getStudentList() {
        ArrayList<Student> student = new ArrayList<Student>();
        for(Observer o:observers){
            if(o instanceof Student){
                student.add((Student) o);
            }
        }
        return student;
    }
    public ArrayList<Character> getGradeList() { return gradeList;   }

    //setters
    public void setBuilding(String inputBuilding) { building = inputBuilding;  }
    public void setRoom(int inputRoom)            { room = inputRoom;          }
//    public void setProfessor(Professor inputProf) { professor = inputProf;     }
    public void addStudent(Student inputStu)      { observers.add(inputStu); }
    public void addGrade(Character inputGrade)    { gradeList.add(inputGrade); }

    public int getState(){
        return state;
    }

    public void setState(int s){
        this.state=state;
    }

    @Override
    public void attachObserver(Observer o) {
        if(observers.contains(o)){
            return;
        }
        observers.add(o);
        o.update(this);
    }

    @Override
    public void detachObserver(Observer o) {
        CourseSection c = this;
        c.setState(-1);
        o.update(c);
        observers.remove(o);
    }

    @Override
    public void notifyAllObserver() {
        for(Observer o: observers){
            o.update(this);
        }
    }
}
