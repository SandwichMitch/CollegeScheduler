package com.example.a2340project1;

public class Task {
    public String taskName;
    public int dueMonth;
    public int dueDay;
    public Task(String taskName, int dueMonth, int dueDay) {
        this.taskName = taskName;
        if (dueMonth > 12 || dueMonth < 1 || dueDay > 31 || dueDay < 1) {
            this.dueMonth = 1;
            this.dueDay = 1;
        } else {
            this.dueMonth = dueMonth;
            this.dueDay = dueDay;
        }
    }

    public String getTaskName() {
        return taskName;
    }
    public String getDueDate() {
        return (dueMonth + "/" + dueDay);
    }
}
