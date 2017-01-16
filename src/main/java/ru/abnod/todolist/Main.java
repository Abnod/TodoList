package ru.abnod.todolist;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;
import ru.abnod.todolist.beans.Task;

import java.util.List;

/**
 * Created by Abnod on 1/15/2017.
 */
public class Main {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void main(String...args){

        serviceRegistry=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources( serviceRegistry ).getMetadataBuilder().build();
        sessionFactory=metadata.getSessionFactoryBuilder().build();

        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();

        //s.save(new Task("azaza",0));

        List<Task> tasks=s.createQuery("from Task").list();

        System.out.println(tasks);

        s.getTransaction().commit();

        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
