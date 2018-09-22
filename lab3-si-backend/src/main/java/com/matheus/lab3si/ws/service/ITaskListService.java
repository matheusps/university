package com.matheus.lab3si.ws.service;

import com.matheus.lab3si.ws.model.TaskList;

import java.util.Collection;

/**
 * Created by Visitor on 1/25/2017.
 */
public interface ITaskListService {

    Collection<TaskList> getAllTaskLists();
    TaskList getTaskListById(long id);
    void createTaskList(TaskList taskList);
    TaskList updateTaskList(TaskList taskList);
    boolean deleteTaskList(long id);
    void deleteAllTaskLists();

}
