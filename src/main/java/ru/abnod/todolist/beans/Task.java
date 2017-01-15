package ru.abnod.todolist.beans;

/**
 * Created by Abnod on 1/15/2017.
 */
public class Task {

    private String task;
    private boolean deleted;

    public Task() {
    }

    public Task(String task, boolean deleted) {
        this.task = task;
        this.deleted = deleted;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
