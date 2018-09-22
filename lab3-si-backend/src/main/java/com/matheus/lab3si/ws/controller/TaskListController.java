package com.matheus.lab3si.ws.controller;

import com.matheus.lab3si.ws.model.TaskList;
import com.matheus.lab3si.ws.service.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/taskList")
public class TaskListController {
    @Autowired
    private ITaskListService taskListService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TaskList>> getAllTaskLists(){
        Collection<TaskList> answer = this.taskListService.getAllTaskLists();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskList> getTaskListById(@PathVariable("id") long id){
        TaskList answer = this.taskListService.getTaskListById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList){
        this.taskListService.createTaskList(taskList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskList> updateTaskList(@RequestBody TaskList taskList){
        TaskList answer = this.taskListService.updateTaskList(taskList);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskList> deleteTaskList(@PathVariable("id") long id){
        boolean answer = this.taskListService.deleteTaskList(id);
        return (answer)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TaskList> deleteAllTaskList(){
        taskListService.deleteAllTaskLists();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
