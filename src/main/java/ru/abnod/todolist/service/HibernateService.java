package ru.abnod.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abnod.todolist.dao.TaskDao;
import ru.abnod.todolist.model.Task;

import java.util.List;

@Service
public class HibernateService {

    @Autowired
    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao){this.taskDao=taskDao;}

    @Transactional
    public void createTask(Task task){
        taskDao.createTask(task);
    }

    @Transactional
    public void deleteTask(int id){
        taskDao.deleteTask(id);
    }

    @Transactional
    public List<Task> getTasks(int pageNumber){
        return taskDao.getTasks(pageNumber);
    }

    @Transactional
    public List<Task> getActiveTasks(int pageNumber){
        return taskDao.getActiveTasks(pageNumber);
    }

    @Transactional
    public List<Task> getCompletedTasks(int pageNumber){
        return taskDao.getCompletedTasks(pageNumber);
    }

    @Transactional
    public void editTask(Task task){
        taskDao.editTask(task);
    }

    @Transactional
    public void markDone(Task task){taskDao.markDone(task);}

    @Transactional
    public void markUndone(Task task){taskDao.markUndone(task);}

    @Transactional
    public Task getTask(int id){return taskDao.getTask(id);}

    @Transactional
    public int getPages(){return taskDao.getPages();}

    @Transactional
    public int getCompletedPages(){return taskDao.getCompletedPages();}

    @Transactional
    public int getActivePages(){return taskDao.getActivePages();}


}
