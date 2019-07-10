package com.wafman.taskmaster.taskmaster.Controller;

import com.wafman.taskmaster.taskmaster.Model.Task;
import com.wafman.taskmaster.taskmaster.Repository.S3Client;
import com.wafman.taskmaster.taskmaster.Repository.TaskRespository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class TaskController {

    @Autowired
    private S3Client s3Client;

//    @Autowired
//    TaskController(S3Client s3Client){
//        this.s3Client = s3Client;
//    }

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
    public ResponseEntity createTasks(@RequestBody Task task, @RequestPart MultipartFile file){
        if(task.getAssignee() != null){
            task.setStatus("Assigned");
        } else {
            task.setStatus("Available");
        }
        taskRespository.save(task);
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}/images")
    public Task createImage(@PathVariable UUID id, @RequestPart(value= "file") MultipartFile file){
        Task task = taskRespository.findById(id).get();
        String pic = this.s3Client.uploadFile(file);
        task.setPic(pic);
        String[] blah = pic.split("/");
        String tb = blah[blah.length -1];
        task.setPicResize("https://taskmaster-react-resized.s3-us-west-2.amazonaws.com/resized-" + tb);
        System.out.println(pic);
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

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable UUID id){ taskRespository.deleteById(id); }

}
