package com.TeamProject.Evaluator;

import com.TeamProject.Course.CourseSection;
import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class OverallVisitor implements Visitor{

    public OverallVisitor(){ }

    /** Pass rate of all semester course(s) of this professor
     * output = 0 -- never taught a course
     * output > 0 -- at least one student pass
     * output < 0 -- all student fail in his/her course(s)
     * */
    @Override
    public double visit(Professor inputProf) {
        double output = 0.0;
        int count = 0;

        if (!inputProf.getPassRates().isEmpty()){
            HashMap<String, ArrayList<Double>> passRates = inputProf.getPassRates();

            for (ArrayList<Double> rate: passRates.values()) {
                for (Double r: rate) {
                    output += r;
                    ++count;
                }
            }

            if(output == 0){ //all student fail in his/her course(s)
                output = -1.0;
            } else { //at least one student pass
                output = output / count;
            }
        } //else never taught a course

        inputProf.setPassRateOverall(output);
        return output;
    }


    /** Student overall GPA calculator
     * output = 0 -- never took a course
     * output > 0 -- pass at least one course
     * output < 0 -- fail all the taken courses
     * */
    @Override
    public double visit(Student inputStu) {
        double output = 0.0;
        int count = 0;

        if (!inputStu.getFinalGrades().isEmpty()){
            HashMap<String, ArrayList<Character>> gradeMap = inputStu.getFinalGrades();

            for (ArrayList<Character> grade: gradeMap.values()) {
                for (Character g: grade) {
                    output += gradeCalculation(g);
                    ++count;
                }
            }

            if(output == 0){ //fail all the taken courses
                output = -1.0;
            } else { //pass at least one course
                output = output / count;
            }
        } //else never took a course

        inputStu.setOverallGPA(output);
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
