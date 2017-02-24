package ru.abnod.todolist.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test")
@org.hibernate.annotations.Entity(
        dynamicUpdate = true
)
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "task")
    private String task="";
    @Column(name = "isDeleted")
    private int done =0; //0-active, 1-completed;

    public Task() {
    }

    public Task(String task,int done) {
        this.task=task;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", deleted=" + done +
                '}';
    }
}
