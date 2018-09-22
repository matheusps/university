package com.matheus.lab3si.ws.service;

import com.matheus.lab3si.ws.model.Subtask;

import java.util.Collection;

/**
 * Created by Visitor on 1/25/2017.
 */
public interface ISubtaskService {

    Collection<Subtask> getAllSubtasks();
    Subtask getSubtaskById(long id);
    void createSubtask(Subtask subtask);
    Subtask updateSubtask(Subtask subtask);
    boolean deleteSubtask(long id);
    void deleteAllSubtasks();

}
