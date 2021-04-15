package com.TeamProject.Person;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Course.Term;
import com.TeamProject.Evaluator.MajorVisitor;
import com.TeamProject.Evaluator.OverallVisitor;
import com.TeamProject.Evaluator.Visitable;
import com.TeamProject.Evaluator.Visitor;
import com.TeamProject.Observer.Subject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection = "Student_table")
public class Student extends Person implements Visitable {
    private static int countID = 100000001;
    private int studentNumber;
    private String major;
    private ArrayList<Term> terms; //terms contain courseSectionIds
    private HashMap<String, ArrayList<Character>> finalGrades; //k-Major, v-list of finalGrade
    private double majorGPA, overallGPA;
    private HashMap<String,Character> finals;

    public Student(){
        super();
        studentNumber = countID;
        major = "";
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        terms = new ArrayList<>();
        finalGrades = new HashMap<>();
        finals = new HashMap<>();
    }

    public Student(String inputName, String inputGender, String inputEmail,
                   LocalDate inputDOB, String inputPW, String inputMajor){
        super(inputName, inputGender, inputEmail, inputDOB, inputPW);
        studentNumber = countID;
        major = inputMajor;
        majorGPA = 0;
        overallGPA = 0;
        ++countID;
        terms = new ArrayList<>();
        finalGrades = new HashMap<>();
        finals = new HashMap<>();
    }

    //getters
    public int getStudentNumber(){ return studentNumber; }
    public String getMajor()     { return major;         }
    public double getMajorGPA()  { return majorGPA;      }
    public double getOverallGPA(){ return overallGPA;    }
    public ArrayList<Term> getTerms(){ return terms; }
    public HashMap<String, ArrayList<Character>> getFinalGrades(){ return finalGrades; }
    public HashMap<String,Character> getFinals(){ return finals;}


    //setters
    public void setMajor(String inputMajor){ major = inputMajor; }
    public void setMajorGPA(double gpa){ majorGPA = gpa; }
    public void setOverallGPA(double gpa){ overallGPA = gpa; }

    public void addFinals(CourseSection c,Character grade){
        finals.put(c.getSectionName(),grade);
    }

    public void addFinalGrade(CourseSection c, Character grade){
        if(finalGrades.containsKey(c.getMajor())){
            finalGrades.get(c.getMajor()).add(grade);
        } else{
            ArrayList<Character> gradeList = new ArrayList<>();
            gradeList.add(grade);
            finalGrades.put(c.getMajor(), gradeList);
        }
    }

    public void addSection(CourseSection section){
        for(Term t:terms){
            if(section.sameTerm(t)){
                for(int sectionID: t.getCourseSections()){
                    if(sectionID == section.getSectionID()){
                        t.removeCourseSections(section);
                        t.addCourseSections(section);
                        return;
                    }
                }
                t.addCourseSections(section);
                return;
            }
        }

        Term added = new Term(section.getTermYear(), section.getTermSeason());
        added.addCourseSections(section);
        terms.add(added);
    }

    public void removeSection(CourseSection section){
        for(Term t:terms){
            if(section.sameTerm(t)){
                for(int sectionID: t.getCourseSections()){
                    if(sectionID == section.getSectionID()){
                        t.removeCourseSections(section);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


    @Override
    public void update(Subject s) {
        if(s instanceof CourseSection){
            addSection((CourseSection) s);
        }
    }

    //Test
    public boolean containCourse(CourseSection c){
        for(Term t:terms){
            if(c.sameTerm(t)){
                for(int sectionID: t.getCourseSections()){
                    if(c.getSectionID() == sectionID){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
