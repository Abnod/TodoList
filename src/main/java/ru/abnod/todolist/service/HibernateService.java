package ru.abnod.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Task> getTasks(){
        return taskDao.getTasks();
    }

    @Transactional
    public void editTask(Task task){
        taskDao.editTask(task);
    }

    @Transactional
    public void markDone(Task task){taskDao.markDone(task);}

    @Transactional
    public Task getTask(int id){return taskDao.getTask(id);}

}
