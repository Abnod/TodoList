package ru.abnod.todolist.web;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abnod.todolist.model.Task;
import ru.abnod.todolist.service.HibernateService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HibernateController {

    @Resource(name = "hibernateService")
    private HibernateService hibernateService;

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String getTasks(Model model){
        List<Task> tasks=hibernateService.getTasks();
        model.addAttribute("tasks",tasks);

        return "tasks_List";
    }

    @RequestMapping (value = "/tasks_List_Completed", method = RequestMethod.GET)
    public String getCompleted(Model model){
        List<Task>sorted=new ArrayList<Task>();

        for (Task task:hibernateService.getTasks()){
            if(task.getDone()==1){
                sorted.add(task);
            }
        }

        model.addAttribute("tasks", sorted);

        return "tasks_List_Completed";
    }

    @RequestMapping (value = "/tasks_List_Active", method = RequestMethod.GET)
    public String getActive(Model model){
        List<Task>sorted=new ArrayList<Task>();

        for (Task task:hibernateService.getTasks()){
            if(task.getDone()==0){
                sorted.add(task);
            }
        }

        model.addAttribute("tasks", sorted);

        return "tasks_List_Active";
    }

    @RequestMapping (value = "/add_task", method = RequestMethod.GET)
    public String getAdd(Model model)
    {
        model.addAttribute("taskAttribute", new Task());

        return "add_task";
    }

    @RequestMapping (value = "/add_task", method = RequestMethod.POST)
    public String add(@ModelAttribute("taskAttribute") Task task)
    {
        hibernateService.createTask(task);

        return "task_added_notification";
    }

    @RequestMapping (value = "/delete_task", method = RequestMethod.GET)
    public String deleteTask(@RequestParam(value = "id") int id, Model model)
    {
        hibernateService.deleteTask(id);
        model.addAttribute("id",id);

        return "task_deleted_notification";
    }

    @RequestMapping (value = "/complete_task", method = RequestMethod.GET)
    public String setCompleted(@RequestParam (value = "id") Integer id, Model model)
    {
        Task task=hibernateService.getTask(id);
        hibernateService.markDone(task);

        return "task_completed_notification";
    }

    @RequestMapping (value = "/edit_task", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id") int id, Model model)
    {
        model.addAttribute("taskAttribute", hibernateService.getTask(id));

        return "edit_task";
    }

    @RequestMapping (value = "/edit_task", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute ("taskAttribute") Task task, @RequestParam (value = "id") int id, Model model)
    {
        task.setId(id);
        hibernateService.editTask(task);
        model.addAttribute("id", id);

        return "task_edited_notification";
    }
}
