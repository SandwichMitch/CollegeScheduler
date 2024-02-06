package com.example.a2340project1;

public class Task implements Comparable<Task> {
    public String taskName;
    public int dueMonth;
    public int dueDay;
    public String time;
    public Task(String taskName, int dueMonth, int dueDay, String time) {
        this.taskName = taskName;
        this.time = time;
        if (dueMonth > 12 || dueMonth < 1 || dueDay > 31 || dueDay < 1) {
            this.dueMonth = 1;
            this.dueDay = 1;
        } else {
            this.dueMonth = dueMonth;
            this.dueDay = dueDay;
        }
    }

    @Override
    public int compareTo(Task other) {
        if (this.dueMonth < other.dueMonth) {
            return -1;
        } else if (this.dueMonth > other.dueMonth){
            return 1;
        } else if (this.dueDay < other.dueDay) {
            return -1;
        }  else if (this.dueMonth == other.dueMonth && this.dueDay > other.dueDay) {
            return 1;
        } else if (this.dueMonth == other.dueMonth && this.dueDay == other.dueDay) {
            return 0;
        } else {
            return 0;
        }
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String newName) {this.taskName = newName; }
    public int getDueMonth() {return dueMonth; }
    public void setDueMonth(int newMonth) {this.dueMonth = newMonth; }
    public int getDueDay() {return dueDay; }
    public void setDueDay(int newDay) {this.dueDay = newDay; }
    public String getDueDate() {
        return (dueMonth + "/" + dueDay);
    }
    public String getTime() {return time; }
    public void setTime(String newTime) {this.time = newTime; }
}
