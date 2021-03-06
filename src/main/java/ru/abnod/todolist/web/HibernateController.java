package ru.abnod.todolist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.abnod.todolist.model.Task;
import ru.abnod.todolist.service.HibernateService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class HibernateController {

    @Resource(name = "hibernateService")
    private HibernateService hibernateService;

    @RequestMapping (value = {"/", "/tasks_List"}, method = RequestMethod.GET)
    public String getTasks(@RequestParam(required = false) Integer page, Model model){
        int pages=(int)Math.ceil(hibernateService.getPages()/5.0);
        if(page==null || page < 1 || page > pages) page = 1;
        List<Task> tasks=hibernateService.getTasks(page);
        model.addAttribute("pages",pages);
        model.addAttribute("tasks",tasks);
        model.addAttribute("page", page);

        return "tasks_List";
    }

    @RequestMapping (value = "/tasks_List_Active", method = RequestMethod.GET)
    public String getActive(@RequestParam(required = false) Integer page, Model model){
        int pages=(int)Math.ceil(hibernateService.getActivePages()/5.0);
        if(page==null || page < 1 || page > pages) page = 1;
        List<Task>sorted=hibernateService.getActiveTasks(page);
        model.addAttribute("pages",pages);
        model.addAttribute("tasks",sorted);
        model.addAttribute("page", page);

        return "tasks_List_Active";
    }


    @RequestMapping (value = "/tasks_List_Completed", method = RequestMethod.GET)
    public String getCompleted(@RequestParam(required = false) Integer page, Model model){
        int pages=(int)Math.ceil(hibernateService.getCompletedPages()/5.0);
        if(page==null || page < 1 || page > pages) page = 1;
        List<Task>sorted=hibernateService.getCompletedTasks(page);
        model.addAttribute("pages",pages);
        model.addAttribute("tasks",sorted);
        model.addAttribute("page", page);

        return "tasks_List_Completed";
    }

    @RequestMapping (value = "/add_task", method = RequestMethod.POST)
    public String getAdd(@RequestParam(value="name", required = false) String name, @RequestParam(required = false) Integer page,
                      @RequestParam(required = false) String sr, Model model)
    {
        hibernateService.createTask(new Task(name, 0));
        int pages=(int)Math.ceil(hibernateService.getPages()/5.0);

        return getRedirect(pages, sr, model);
    }

    @RequestMapping (value = "/delete_task", method = RequestMethod.POST)
    public String deleteTask(@RequestParam(value = "id") int id, @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "sr", required = false) String sr, Model model)
    {
        hibernateService.deleteTask(id);

        if(!(sr==null) && sr.equals("ac")){
            int pages=(int)Math.ceil(hibernateService.getActivePages()/5.0);
            if(page>pages){page=pages;}
        } else if(!(sr==null) && sr.equals("co")){
            int pages=(int)Math.ceil(hibernateService.getCompletedPages()/5.0);
            if(page>pages){page=pages;}
        } else {
            int pages=(int)Math.ceil(hibernateService.getPages()/5.0);
            if(page>pages){page=pages;}
        }

        return getRedirect(page, sr, model);
    }

    @RequestMapping (value = "/complete_task", method = RequestMethod.POST)
    public String setCompleted(@RequestParam (value = "id") Integer id, @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "sr", required = false) String sr, Model model)
    {
        Task task=hibernateService.getTask(id);
        System.out.println(task.getDone());
        if (task.getDone()==0){
            hibernateService.markDone(task);
        } else {hibernateService.markUndone(task);}
        return getRedirect(page, sr, model);
    }

    @RequestMapping (value = "/edit_task", method = RequestMethod.POST)
    public String saveEdit( @RequestParam(value = "edit_text") String name, @RequestParam (value = "id") int id,
                            @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sr", required = false) String sr,
                            Model model)
    {
        Task task = hibernateService.getTask(id);
        task.setId(id);
        task.setTask(name);
        hibernateService.editTask(task);

        return getRedirect(page, sr, model);
    }

    private String getRedirect(Integer page, String sr, Model model) {
        if(!(sr==null) && sr.equals("ac")){
            return "redirect: /tasks_List_Active?page="+page;
        } else if(!(sr==null) && sr.equals("co")){
            return "redirect: /tasks_List_Completed?page="+page;
        } else return "redirect: /tasks_List?page="+page;
    }
}