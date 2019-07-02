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

    //consider making an html file to handle all of this
    @GetMapping("/tasks")
    public Iterable<Task> getTasks(){
        return taskRespository.findAll();
    }

//use postman key value pairs key == title, value == what you call it
    // /tasks/?title=create new Tasks&description=Makea list of all the things we need to do
    @PostMapping("/tasks")
    public RedirectView createTasks(@RequestParam String title, @RequestParam String description, @RequestParam String status){
        Task task = new Task(title, description, status);
        taskRespository.save(task);
        return new RedirectView("/tasks");
    }

    //if else checks to change status
//    @PutMapping("/tasks/{id}/state")
//    public String updateTasksState(){
//
//    }

}
