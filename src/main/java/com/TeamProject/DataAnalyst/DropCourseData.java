package com.TeamProject.DataAnalyst;

import com.TeamProject.Course.Course;
import com.TeamProject.Person.Student;
import java.util.Date;

public class DropCourseData extends Data {
    Course course;

    public DropCourseData(Student stu, Date d, Course c) {
        super(stu, d);
        this.action = "DROP";
        course = c;
    }

    @Override
    public String getValue() {
        return course.getCourseName();
    }
}
