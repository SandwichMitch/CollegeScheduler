package com.example.a2340project1;

public class Exam extends Task {

    private String location;
    public Exam(String taskName, int dueMonth, int dueDay, String location) {
        super(taskName, dueMonth, dueDay);
        this.location = location;
    }

    public String getLocation() {return this.location; }
}
