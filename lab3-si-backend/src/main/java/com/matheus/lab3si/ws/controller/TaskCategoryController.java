package com.matheus.lab3si.ws.controller;

import com.matheus.lab3si.ws.model.TaskCategory;
import com.matheus.lab3si.ws.service.ITaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/category")
public class TaskCategoryController {
    @Autowired
    private ITaskCategoryService taskCategoryService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TaskCategory>> getAllTaskCategories(){
        Collection<TaskCategory> answer = this.taskCategoryService.getAllTaskCategories();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> getTaskCategtoryById(@PathVariable("id") long id){
        TaskCategory answer = this.taskCategoryService.getTaskCategoryById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> createTaskCategory(@RequestBody TaskCategory taskCategory){
        this.taskCategoryService.createTaskCategory(taskCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> updateTaskCategory(@RequestBody TaskCategory taskCategory){
        TaskCategory answer = this.taskCategoryService.updateTaskCategory(taskCategory);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskCategory> deleteTaskCategory(@PathVariable("id") long id){
        boolean answer = this.taskCategoryService.deleteTaskCategory(id);
        return (answer)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TaskCategory> deleteAllTaskCateogries(){
        taskCategoryService.deleteAllCategories();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
