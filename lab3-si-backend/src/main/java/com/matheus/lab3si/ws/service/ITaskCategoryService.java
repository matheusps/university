package com.matheus.lab3si.ws.service;

import com.matheus.lab3si.ws.model.TaskCategory;

import java.util.Collection;

/**
 * Created by Visitor on 1/25/2017.
 */
public interface ITaskCategoryService {

    Collection<TaskCategory> getAllTaskCategories();
    TaskCategory getTaskCategoryById(long id);
    void createTaskCategory(TaskCategory category);
    TaskCategory updateTaskCategory(TaskCategory category);
    boolean deleteTaskCategory(long id);
    void deleteAllCategories();

}
