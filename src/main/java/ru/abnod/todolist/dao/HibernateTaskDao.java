package ru.abnod.todolist.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.abnod.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 17.01.2017.
 */
public class HibernateTaskDao implements TaskDao{

    @Autowired
    SessionFactory sessionFactory;

    public void createTask(Task task){
        sessionFactory.getCurrentSession().save(task);
    }

    public void deleteTask(Task task){
        sessionFactory.getCurrentSession().delete(task);
    }

    public List<Task> getTasksByCompl(int completion){
        List<Task> tasks=null;
        //0 active, 1 completed, 2 all
        switch (completion){
            case 0:{}
            case 1:{}
            case 2:{tasks=sessionFactory.getCurrentSession().createQuery("from Task").list();}
        }
        return tasks;
    }

    public void editTask(Task task){

    }
}
