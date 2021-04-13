package com.TeamProject.Course;

import com.TeamProject.Observer.Observer;
import com.TeamProject.Observer.Subject;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "CourseSection_table")
public class CourseSection implements Subject {
    private static int count  = 100001;
    private int sectionID;

    private Course course;
    private Character section;
    private int cYear;
    private String cSeason;

    private int room;
    private String building;

    private ArrayList<Observer> observers;
    private Map<Student, Character> gradeList;
    private ArrayList<Deliverable> deliverables;
    private int state;

    public CourseSection(Course course, Character section, int cYear,String cSeason){
        sectionID = count;
        ++count;
        state = 0;

        this.course = course;
        this.section = section;
        this.cYear = cYear;
        this.cSeason = cSeason;

        room = 0;
        building = "";

        observers = new ArrayList<>();
        deliverables = new ArrayList<>();
        gradeList = new HashMap<>();
    }

    public boolean sameTerm(Term t){
        return cYear == t.getYear() && cSeason.equals(t.getSeason());
    }


    //getters
    public int getSectionID()      { return sectionID; }
    public String getSectionName() { return course.getCourseName() + section; }
    public int getTermYear()        { return cYear; }
    public String getTermSeason()     { return cSeason; }
    public String getTerm() { return cSeason + cYear;}
    public int getCode() {return course.getCode();}
    public int    getRoom()        { return room;     }
    public String getBuilding()    { return building; }
    public String getMajor() { return course.getMajor(); }
    public ArrayList<Observer> getObservers() { return observers; }
    public ArrayList<Deliverable> getDeliverables() { return deliverables; }
    public int getState(){
        return state;
    }
    public char getSection() {return section;}
    public Professor getProfessor() {
        Professor professor = null;
        for(Observer o:observers){
            if(o instanceof Professor){
                professor=(Professor) o;
            }
        }
        return professor;
    }

    public ArrayList<Student> getStudentList() {
        ArrayList<Student> student = new ArrayList<>();
        for(Observer o:observers){
            if(o instanceof Student){
                student.add((Student) o);
            }
        }
        return student;
    }

    public Map<Student,Character> getGradeList() { return gradeList;   }
    //public Character getGradeByName(String name){ return gradeList.get(name); }

    //setters
//    public void setBuilding(String inputBuilding) { building = inputBuilding;  }
//    public void setRoom(int inputRoom)            { room = inputRoom;          }
    public void setProfessor(Professor inputProf) {
        for(Observer o:observers){
            if(o instanceof Professor){
                detachObserver(o);
                attachObserver(inputProf);
                return;
            }
        }
        attachObserver(inputProf);
    }
    public void addStudent(Student inputStu)      { observers.add(inputStu);
        this.notifyAllObserver();
        gradeList.put(inputStu,'F');
    }
    public void addGrade(Student s,Character inputGrade)    { gradeList.replace(s,inputGrade); }
    public void setGrade(Student s,Character inputGrade)    { gradeList.replace(s,inputGrade); }
    public boolean addDeliverable(String name,LocalDate inputLocalDate)      {
        for(Deliverable d:deliverables){
            if(!d.getName().equals(name)){
                deliverables.add(new Deliverable(name,inputLocalDate));
                return true;
            }
        }
        return false;
    }


    public void setState(int s){
        state = s;
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
