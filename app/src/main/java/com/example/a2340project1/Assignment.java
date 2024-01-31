package com.example.a2340project1;

public class Assignment extends Task {
    public String assignmentType;
    public Assignment(String taskName, int dueMonth, int dueDay, String assignmentType) {
        super(taskName, dueMonth, dueDay);
        this.assignmentType = assignmentType;
    }

    public String getAssignmentType() {
        return this.assignmentType;
    }

}
