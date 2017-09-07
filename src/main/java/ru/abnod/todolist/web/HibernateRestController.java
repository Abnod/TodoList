//package ru.abnod.todolist.web;
//
//import org.springframework.web.bind.annotation.*;
//import ru.abnod.todolist.model.Task;
//import ru.abnod.todolist.service.HibernateService;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RestController
//@RequestMapping("/json")
//public class HibernateRestController {
//
//    @Resource(name = "hibernateService")
//    private HibernateService hibernateService;
//
//    @RequestMapping (value = {"/", "/tasks_List"}, method = RequestMethod.GET)
//    public List<Task> getTasks(@RequestParam(required = false) Integer page){
//        int pages=(int)Math.ceil(hibernateService.getPages()/5.0);
//        if(page==null || page < 1 || page > pages) page = 1;
//        List<Task> tasks=hibernateService.getTasks(page);
//
//        return tasks;
//    }
//
//TEST REST
