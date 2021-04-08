package com.TeamProject.Evaluator;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

import java.util.ArrayList;
import java.util.Map;

public class MajorVisitor implements Visitor{

    public MajorVisitor(){ }

    /** Pass rate of the current semester course(s) of this professor
     * output = 0 -- no course for this semester
     * output > 0 -- at least one student pass
     * output < 0 -- all student fail in his/her course(s)
     * */
    @Override
    public double visit(Professor inputProf) {
        double output = 0.0;
        int count = 0;

        if(!inputProf.getTerms().isEmpty()) {
            ArrayList<CourseSection> sections = inputProf.getTerms().get((inputProf.getTerms().size()-1)).getCourseSections();

            for (CourseSection s: sections) {
                output += inputProf.getPassRates().get(s);
                ++count;
            }

            if (output == 0) { //all student fail in his/her course(s) this term
                output = -1.0;
            } else { //at least one student pass this term
                output = output / count;
            }
        } //else no course for this semester

        inputProf.setPassRateOfCurr(output);
        return output;
    }


    /** Student major courses GPA calculator
     * output = 0 -- never took a course
     * output > 0 -- pass at least one course
     * output < 0 -- fail all the taken courses
     * */
    @Override
    public double visit(Student inputStu) {
        double output = 0.0;
        int     count = 0;

        if (!inputStu.getFinalGrades().isEmpty()){
            Map<CourseSection,Character> gradeMap = inputStu.getFinalGrades();

            for (CourseSection key : gradeMap.keySet()) {
                if(key.getMajor().equals(inputStu.getMajor())) {
                    output += gradeCalculation(gradeMap.get(key));
                    ++count;
                }
            }

            if(output == 0){ //fail all the taken major courses
                output = -1.0;
            } else { //pass at least one major course
                output = output / count;
            }
        } //else never took a major course

        inputStu.setMajorGPA(output);
        return output;
    }

    public double gradeCalculation(Character grade){
        if (grade == 'A') {
            return 4.0;
        } else if (grade == 'B') {
            return 3.0;
        } else if (grade == 'C') {
            return 2.0;
        } else if (grade == 'D') {
            return 1.0;
        }
        return 0.0;
    }
}
