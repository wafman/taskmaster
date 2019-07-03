package com.wafman.taskmaster.taskmaster.Model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "TaskMaster")
public class Task {
    //instance variables
    private UUID id;
    private String title;
    private String description;
    private String assignee;
    private String status;

    //constructors
    public Task(){}

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        this.status = "Available";
    }

    public Task(String title, String description, String assignee){
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.status = "Assigned";
    }


    //getters && setters

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getTitle() {
        return title;
    }

    @DynamoDBAttribute
    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
