package com.wafman.taskmaster.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class TaskController {

    @Autowired
    TaskRespository taskRespository;

    @GetMapping("/")
    public String getHome(){
        return "THIS WORKS";
    }

    @GetMapping("/tasks")
    public Iterable<Task> getTasks(){
        return taskRespository.findAll();
    }

    @PostMapping("/tasks")
    public RedirectView createTasks(@RequestParam String title, @RequestParam String description, @RequestParam String status){
        Task task = new Task(title, description, status);
        taskRespository.save(task);
        return new RedirectView("/tasks");
    }

//    @PutMapping("/tasks/{id}/state")
//    public String updateTasksState(){
//
//    }

}
