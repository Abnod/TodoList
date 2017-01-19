package ru.abnod.todolist.dao;

import ru.abnod.todolist.model.Task;

import java.util.List;

/**
 * Created by Oleg on 17.01.2017.
 */
public interface TaskDao {

    public void createTask(Task task);
    public void deleteTask(Task task);
    public List<Task> getTasks();
    public void editTask(Task task);

}
