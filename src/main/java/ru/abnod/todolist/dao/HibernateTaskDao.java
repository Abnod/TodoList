package ru.abnod.todolist.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.abnod.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 17.01.2017.
 */
@Repository
public class HibernateTaskDao implements TaskDao{

    @Autowired
    SessionFactory sessionFactory;

    public void createTask(Task task){
        sessionFactory.getCurrentSession().save(task);
    }

    public void deleteTask(Task task){
        sessionFactory.getCurrentSession().delete(task);
    }

    public List<Task> getTasks(){
        return sessionFactory.getCurrentSession().createQuery("from Task").list();
    }

    public void editTask(Task task){
        sessionFactory.getCurrentSession().update(task);
    }
}
