# Lab 26: TaskMaker

## Description
- application to manage tasks that are created.
- tasks can be created
- tasks can be assigned
- tasks status can be updated
- tasks for assignees can be displayed

## API
- ```@GetMapping("/tasks")```
  - returns json data from the dynamodb
- ```@PostMapping("/tasks)```
  - should post tasks to the dynamodb
- ```@PutMapping("/tasks/{id}/state")```
  - should update task status
- ```@GetMapping("/users/{assignee}/tasks) ```
  - should show the tasks that are assigned to that assignee
- ```@PutMapping("/tasks/{id}/assign/{assignee}) ```
  - can assign tasks to an assignee
- ```@DeleteMapping("/tasks/{id}")```
  - can delete a task
-  ```PostMapping("/tasks/{id}/images")
  - can update a task with an image


## Directions
- from IntelliJ
  - open application
  - run the App.java
- from Command Line
  - test
    - ```./gradlew test```
  - Start Server
    - ```./gradlew bootRun```
    - proceeed in broswer to following routes
      - ```localhost:5000/tasks```
      - ```localhost:5000/tasks/<insert title, description, assignee>```
      - ```localhost:5000/tasks/{id}/state```
      - ```localhost:5000/users/{assignee}/tasks```
      - ```localhost:5000/tasks/{id}/assign/{assignee}```
      - ```localhost:5000/tasks/{id}```
      - ```localhost:5000/tasks/{id}/images```
        - delete route

## Deployed
- http://taskmaster-dev-2.us-west-2.elasticbeanstalk.com/
  - to test routes replace localhost:5000 with the above link
   