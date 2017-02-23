package ru.abnod.todolist.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.abnod.todolist.model.Task;

import java.util.List;

@Repository
public class HibernateTaskDao implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;
    private static int pageSize = 5;

    public void createTask(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

    public void deleteTask(int id) {
        Task task = sessionFactory.getCurrentSession().get(Task.class, id);
        sessionFactory.getCurrentSession().delete(task);
    }

    public List<Task> getTasks(int pageNumber) {
        return sessionFactory.getCurrentSession().createQuery("from Task").setFirstResult(pageSize * (pageNumber-1)).setMaxResults(pageSize).list();
    }

    public List<Task> getCompletedTasks(int pageNumber) {
        return sessionFactory.getCurrentSession().createQuery("from Task where done = 1").setFirstResult(pageSize * (pageNumber-1)).setMaxResults(pageSize).list();
    }

    public List<Task> getActiveTasks(int pageNumber) {
        return sessionFactory.getCurrentSession().createQuery("from Task where done = 0").setFirstResult(pageSize * (pageNumber-1)).setMaxResults(pageSize).list();
    }

    public Task getTask(int id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    public void editTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
    }

    public void markDone(Task task) {
        task.setDone(1);
        editTask(task);
    }

    public int getPages(){
        return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from Task").uniqueResult()).intValue();
    }

    public int getActivePages(){
        return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from Task where done = 0").uniqueResult()).intValue();
    }

    public int getCompletedPages(){
        return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from Task where done = 1").uniqueResult()).intValue();
    }

}
