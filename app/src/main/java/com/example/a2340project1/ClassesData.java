package com.example.a2340project1;

import java.util.ArrayList;
import java.util.List;

public class ClassesData {
    public String className;
    public List<Exam> examList;
    public List<Assignment> assignmentList;

    public ClassesData(String className) {
        this.className = className;
        this.examList = new ArrayList<>();
        examList.add(new Exam("Test Exam", 1, 1, "8 am", "Howey"));
        examList.add(new Exam("Test Exam 2", 1, 2, "12 pm","CULC"));
        this.assignmentList = new ArrayList<>();
    }

    public void addExam(Exam exam) {
        this.examList.add(exam);
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
    }

    public List<Exam> getExamList() {
        return this.examList;
    }

    public void setClassName(String newClassName) {
        this.className = newClassName;
    }
}
