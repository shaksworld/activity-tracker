package com.fragile.activity_tracker.controller;

import com.fragile.activity_tracker.entity.Task;
import com.fragile.activity_tracker.entity.User;
import com.fragile.activity_tracker.enums.Status;
import com.fragile.activity_tracker.error.UserNotFoundException;
import com.fragile.activity_tracker.service.TaskService;
import com.fragile.activity_tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

//    @GetMapping("/tasks")
//    public String showTasks(Model model) {
//        List<Task> tasks = taskService.getAllTask();
//        model.addAttribute("tasks", tasks);
//        return "tasks";
//    }
//
//    @GetMapping("/task/{id}")
//    public String showTask(@PathVariable("id") Long id, Model model) throws UserNotFoundException {
//        Task task = taskService.getTask(id);
//        model.addAttribute("task", task);
//        return "task";
//    }
//
//    @PostMapping("/task/update")
//    public String updateTask(Task task, Model model) {
//        taskService.updateTask(task);
//        model.addAttribute("task", task);
//        return "task";
//    }
//
//    @GetMapping("/task/create")
//    public String createTask(Model model) {
//        model.addAttribute("task", new Task());
//        return "createTask";
//    }
//
//    @PostMapping("/task/save")
//    public String saveTask(Task task, Model model) {
//        taskService.createTask(task);
//        model.addAttribute("task", task);
//        return "task";
//    }

    static List<String> statusList = null;

    static {
        statusList = new ArrayList<>();
        statusList.add("ACTIVE");
        statusList.add("PENDING");
        statusList.add("COMPLETED");
    }


    @GetMapping("/addTask")
    public String showFormPage(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addTask";
    }

    @PostMapping("/addTask")
    public String createTask(@ModelAttribute("task") Task task, HttpServletRequest request) throws UserNotFoundException {
        if (task.getTitle() == null || task.getDescription() == null) {
            return "redirect:/dashboard";
        }
        Long id = (Long) request.getSession().getAttribute("id");
        User currentUser = userService.findUserById(id);
        task.setUsers(currentUser);
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Status.PENDING);
        taskService.createTask(task);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String home(Model model, HttpServletRequest request) {

        String username = (String) request.getSession().getAttribute("username");
        Long userId = (Long) request.getSession().getAttribute("id");
        System.out.println(userId);
        if (username == null) return "redirect:/login";

        List<Task> tasks = taskService.findAllTaskByUserId(userId);
        tasks.sort(Comparator.reverseOrder());
        model.addAttribute("statusList", statusList);
        model.addAttribute("tasks", tasks);

        return "dashboard";
    }





    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model, HttpServletRequest request) throws UserNotFoundException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) return "redirect:/login";

        Task task = taskService.getTask(id);
        model.addAttribute("task", task);

        return "update_task";
    }

    @PostMapping("/task/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task) throws UserNotFoundException {
        Task updateTask = taskService.getTask(id);
        updateTask.setId(id);
        updateTask.setUpdatedAt(LocalDateTime.now());
        updateTask.setStatus(Status.ACTIVE);
        updateTask.setDescription(task.getDescription());
        updateTask.setTitle(task.getTitle());

        taskService.updateTask(updateTask);

        return "redirect:/dashboard";
    }


//
//    @GetMapping("/tasks")
//    public String tasks(Model model) {
//        model.addAttribute("tasks", taskService.getAllTask());
//
//        return "dashboard";
//    }



    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id, HttpServletRequest request) {
        taskService.deleteTask(id);

        return "redirect:/dashboard";
    }


    @GetMapping("/forward/{id}")
    public String changeTaskStatusToNext(@PathVariable(value = "id") Long id){
        this.taskService.changeTaskStatusToNext(id);

        return "redirect:/dashboard";
    }
    @GetMapping("/done/{id}")
    public String changeTaskToDone(@PathVariable(value = "id") Long id){
        this.taskService.changeTaskToDone(id);
        return "redirect:/dashboard";
    }
    @GetMapping("/backward/{id}")
    public String changeTaskStatusToNextBackWard(@PathVariable(value = "id") Long id){
        this.taskService.changeTaskStatusToNextBackWard(id);
        return "redirect:/dashboard";
    }



}
