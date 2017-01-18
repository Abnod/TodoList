package ru.abnod.todolist.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Abnod on 1/15/2017.
 */
@Entity
@Table(name = "test")
public class Task implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "task")
    private String task="";
    @Column(name = "isDeleted")
    private int deleted=0;

    public Task() {
    }

    public Task(String task,int deleted) {
        this.task=task;
        this.deleted=deleted;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
