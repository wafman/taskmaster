# Lab 26: TaskMaker

## Description
- application to manage tasks that are created.

## API
- ```@GetMapping("/tasks")```
  - returns json data from the dynamodb
- ```@PostMapping("/tasks)```
  - should post data to the dynamodb
- ```@PutMapping("/tasks/{id}/state")```
  - should update task data


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
      - ```localhost:8080/tasks```
      - ```localhost:8080/tasks/```
      - ```localhost:8080/tasks/{id}/state```
   