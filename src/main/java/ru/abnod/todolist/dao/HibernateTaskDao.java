package ru.abnod.todolist.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.abnod.todolist.model.Task;

import java.util.List;

@Repository
public class HibernateTaskDao implements TaskDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void createTask(Task task){
        sessionFactory.getCurrentSession().save(task);
    }

    public void deleteTask(int id){
        Task task = (Task) sessionFactory.getCurrentSession().get(Task.class,id);
        sessionFactory.getCurrentSession().delete(task);
    }

    public List<Task> getTasks(){
        return sessionFactory.getCurrentSession().createQuery("from Task").list();
    }

    public Task getTask(int id){
        return sessionFactory.getCurrentSession().get(Task.class,id);
    }

    public void editTask(Task task){
        sessionFactory.getCurrentSession().update(task);
    }

    public void markDone(Task task){task.setDone(1);editTask(task);}
}
