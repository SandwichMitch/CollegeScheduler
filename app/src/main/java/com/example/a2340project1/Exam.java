package com.example.a2340project1;

public class Exam extends Task {

    private String location;
    public Exam(String taskName, int dueMonth, int dueDay, String time, String location) {
        super(taskName, dueMonth, dueDay, time);
        this.location = location;
    }

    public String getLocation() {return this.location; }
}
