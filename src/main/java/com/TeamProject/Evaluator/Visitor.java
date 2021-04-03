package com.TeamProject.Evaluator;

import com.TeamProject.Person.Professor;
import com.TeamProject.Person.Student;

public interface Visitor {
    double visit(Professor inputProf);
    double visit(Student   inputStu);
}
