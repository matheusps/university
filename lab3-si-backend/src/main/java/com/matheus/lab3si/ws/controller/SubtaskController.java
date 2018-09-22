package com.matheus.lab3si.ws.controller;

import com.matheus.lab3si.ws.model.Subtask;
import com.matheus.lab3si.ws.service.ISubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping( value = "/subtask")
public class SubtaskController {

    @Autowired
    private ISubtaskService subtaskService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Subtask>> getAllSubtasks(){
        Collection<Subtask> answer = this.subtaskService.getAllSubtasks();
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subtask> getSubtaskById(@PathVariable("id") long id){
        Subtask answer = this.subtaskService.getSubtaskById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subtask> createSubtask(@RequestBody Subtask subtask){
        this.subtaskService.createSubtask(subtask);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subtask> updateSubtask(@RequestBody Subtask subtask){
        Subtask answer = this.subtaskService.updateSubtask(subtask);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subtask> deleteSubtask(@PathVariable("id") long id){
        boolean answer = this.subtaskService.deleteSubtask(id);
        return (answer)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Subtask> deleteAllSubtasks(){
        subtaskService.deleteAllSubtasks();
        return new ResponseEntity<Subtask>(HttpStatus.OK);
    }
}
