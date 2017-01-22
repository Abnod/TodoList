package ru.abnod.todolist.dao;

import ru.abnod.todolist.model.Task;

import java.util.List;

public interface TaskDao {

    void createTask(Task task);
    void deleteTask(int id);
    List<Task> getTasks();
    void editTask(Task task);
    void markDone(Task task);
    Task getTask(int id);

}
