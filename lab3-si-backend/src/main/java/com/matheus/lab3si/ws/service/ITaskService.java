package com.matheus.lab3si.ws.service;

import com.matheus.lab3si.ws.model.Task;

import java.util.Collection;

/**
 * Created by Visitor on 1/24/2017.
 */
public interface ITaskService {

    Collection<Task> getAllTasks();
    Task getTaskById(long id);
    void createTask(Task task);
    Task updateTask(Task task);
    boolean deleteTask(long id);
    void deleteAllTasks();

}
