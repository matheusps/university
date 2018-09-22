package com.matheus.lab3si.ws.controller;

import com.matheus.lab3si.ws.model.Task;
import com.matheus.lab3si.ws.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Task>> getAllTasks(){
        Collection<Task> answer = this.taskService.getAllTasks();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id){
        Task answer = this.taskService.getTaskById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        this.taskService.createTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task answer = this.taskService.updateTask(task);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteTask(@PathVariable("id") long id){
        boolean answer = this.taskService.deleteTask(id);
        return (answer)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Task> deleteAllTasks(){
        taskService.deleteAllTasks();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
