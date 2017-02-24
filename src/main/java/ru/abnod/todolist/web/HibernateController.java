package ru.abnod.todolist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping (value = "/add_task", method = RequestMethod.GET)
    public String getAdd(@RequestParam(required = false) Integer page, @RequestParam(required = false) String sr, Model model)
    {
        model.addAttribute("taskAttribute", new Task());
        return "add_task";
    }

    @RequestMapping (value = "/add_task", method = RequestMethod.POST)
    public String add(@ModelAttribute("taskAttribute") Task task, @RequestParam(required = false) Integer page,
                      @RequestParam(required = false) String sr, Model model)
    {
        hibernateService.createTask(task);
        int pages=(int)Math.ceil(hibernateService.getPages()/5.0);

        return getTasks(pages,model);
    }

    @RequestMapping (value = "/delete_task", method = RequestMethod.GET)
    public String deleteTask(@RequestParam(value = "id") int id, @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String sr, Model model)
    {
        hibernateService.deleteTask(id);
        model.addAttribute("id",id);

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

    @RequestMapping (value = "/complete_task", method = RequestMethod.GET)
    public String setCompleted(@RequestParam (value = "id") Integer id, @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) String sr, Model model)
    {
        Task task=hibernateService.getTask(id);
        hibernateService.markDone(task);

        return getRedirect(page, sr, model);
    }

    @RequestMapping (value = "/edit_task", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id") int id, @RequestParam(required = false) Integer page,
                          @RequestParam(required = false) String sr, Model model)
    {
        model.addAttribute("taskAttribute", hibernateService.getTask(id));
        model.addAttribute("page", page);
        model.addAttribute("sr", sr);
        return "edit_task";
    }

    @RequestMapping (value = "/edit_task", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute ("taskAttribute") Task task, @RequestParam (value = "id") int id,
                           @RequestParam(required = false) Integer page, @RequestParam(required = false) String sr, Model model)
    {
        task.setId(id);
        hibernateService.editTask(task);
        model.addAttribute("id", id);

        return getRedirect(page, sr, model);
    }

    private String getRedirect(Integer page, String sr, Model model) {
        if(!(sr==null) && sr.equals("ac")){
            return getActive(page, model);
        } else if(!(sr==null) && sr.equals("co")){
            return getCompleted(page, model);
        } else return getTasks(page,model);
    }
}