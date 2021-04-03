package com.TeamProject.Evaluator;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

public class OverallVisitor implements Visitor{

    public OverallVisitor(){ }

    /** Pass rate of all semester course(s) of this professor
     * output = 0 -- no course for this semester
     * output > 0 -- at least one student pass
     * output < 0 -- all student fail in his/her course(s)
     * */
    @Override
    public double visit(Professor inputProf) {
        double output = 0.0;

        if (inputProf.getPassRateCol().isEmpty()){
            inputProf.setPassRateOfCurr(output);
            return output;
        }else {
            for (double rate : inputProf.getPassRateCol()) {
                output += rate;
            }

            output = output / inputProf.getPassRateCol().size();

            //means this processor has no student passed in his/her courses
            if(output == 0){
                output = -1.0;
            }
        }

        inputProf.setPassRateOfCurr(output);
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

        if (inputStu.getFinalGrades().isEmpty()){
            inputStu.setMajorGPA(output);
            return output;
        }else {
            for (Character grade : inputStu.getFinalGrades()) {
                if (grade == 'A') {
                    output += 4.0;
                } else if (grade == 'B') {
                    output += 3.0;
                } else if (grade == 'C') {
                    output += 2.0;
                } else if (grade == 'D') {
                    output += 1.0;
                } else if (grade == 'F') {
                    output += 0.0;
                }
            }

            output = output / inputStu.getFinalGrades().size();

            //means this student has all course fail
            if(output == 0){
                output = -1.0;
            }
        }

        inputStu.setMajorGPA(output);
        return output;
    }
}
