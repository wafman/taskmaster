package com.wafman.taskmaster.taskmaster.Controller;

import com.wafman.taskmaster.taskmaster.Model.Task;
import com.wafman.taskmaster.taskmaster.Repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    TaskRespository taskRespository;


    //get requests
    @GetMapping("/")
    public String getHome(){
        return "THIS WORKS";
    }

    @GetMapping("/tasks")
    public Iterable<Task> getTasks(){
        return taskRespository.findAll();
    }

    @GetMapping("/users/{assignee}/tasks")
    public List<Task> getAssigneeTasks(@PathVariable String assignee){
        return taskRespository.findByAssignee(assignee);
    }

    //post requests
    @PostMapping("/tasks")
    public @ResponseBody Task createTasks(@ModelAttribute Task task){
        if(task.getAssignee() != null){
            task.setStatus("Assigned");
        } else {
            task.setStatus("Available");
        }
        taskRespository.save(task);
        return task;
    }

    //put requests
    @PutMapping("/tasks/{id}/state")
    public void updateTasksState(@PathVariable UUID id){
        Task task = taskRespository.findById(id).get();
        String status = task.getStatus();
        if(status.equals("Available")){
            task.setStatus("Assigned");
        } else if(status.equals("Assigned")){
            task.setStatus("Accepted");
        } else if(status.equals("Accepted")){
            task.setStatus("Finished");
        }
        taskRespository.save(task);
    }

    @PutMapping("/tasks/{id}/assign/{assignee}")
    public void updateSomething(@PathVariable UUID id, @PathVariable String assignee){
        Task task = taskRespository.findById(id).get();
        task.setAssignee(assignee);
        task.setStatus("Assigned");
        taskRespository.save(task);
    }

}
