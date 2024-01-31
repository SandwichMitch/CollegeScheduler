package com.example.a2340project1;

public class Exam extends Task {
    public String classTag;
    public Exam(String taskName, int dueMonth, int dueDay, String classTag) {
        super(taskName, dueMonth, dueDay);
        this.classTag = classTag;
    }

    public String getClassTag() {
        return this.classTag;
    }
}
