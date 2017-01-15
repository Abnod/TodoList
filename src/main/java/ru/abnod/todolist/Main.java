package ru.abnod.todolist;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.abnod.todolist.beans.Task;

/**
 * Created by Abnod on 1/15/2017.
 */
public class Main {

    public static void main(String...args){
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});

        JdbcTemplate template=context.getBean("jdbcTemplate",JdbcTemplate.class);

        int taskCount=template.queryForObject("select count(*) from test",Integer.class);
        System.out.println(taskCount);
    }
}
