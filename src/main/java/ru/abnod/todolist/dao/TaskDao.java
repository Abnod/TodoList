package ru.abnod.todolist.dao;

import ru.abnod.todolist.model.Task;

import java.util.List;

public interface TaskDao {

    void createTask(Task task);
    void deleteTask(int id);
    List<Task> getTasks(int pageNumber);
    List<Task> getCompletedTasks(int pageNumber);
    List<Task> getActiveTasks(int pageNumber);
    void editTask(Task task);
    void markDone(Task task);
    void markUndone(Task task);
    Task getTask(int id);
    int getPages();
    int getActivePages();
    int getCompletedPages();

}
