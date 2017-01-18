package ru.abnod.todolist.service;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.abnod.todolist.dao.TaskDao;
import ru.abnod.todolist.model.Task;

import java.util.List;

/**
 * Created by Oleg on 17.01.2017.
 */
@Controller
public class HibernateService {

    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao){this.taskDao=taskDao;}

    @Transactional
    public void createTask(Task task){
        taskDao.createTask(task);
    }

    @Transactional
    public void deleteTask(Task task){
        taskDao.deleteTask(task);
    }

    @Transactional
    public List<Task> getTasksByCompl(int completion){
        return taskDao.getTasksByCompl(2);
    }

    public void editTask(Task task){

    }

}
