package com.TeamProject.Evaluator;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

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

        //TODO: identify which course teaching currently, and do calculation

        if(count != 0) {
            output = output / count;
        }

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
        int count = 0;

        //TODO: identify the major courses, and do the calculation

        if(count != 0) {
            output = output / count;
        }

        inputStu.setMajorGPA(output);
        return output;
    }
}
