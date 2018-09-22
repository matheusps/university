package com.matheus.lab3si.ws.service.implementation;

import com.matheus.lab3si.ws.model.TaskCategory;
import com.matheus.lab3si.ws.repository.ITaskCategoryRepository;
import com.matheus.lab3si.ws.service.ITaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by matheus on 25/01/17.
 */
@Service
public class TaskCategoryService implements ITaskCategoryService {

    @Autowired
    ITaskCategoryRepository taskCategoryRepository;

    @Override
    public Collection<TaskCategory> getAllTaskCategories() {
        return this.taskCategoryRepository.findAll();
    }

    @Override
    public TaskCategory getTaskCategoryById(long id) {
        return this.taskCategoryRepository.findOne(id);
    }

    @Override
    public void createTaskCategory(TaskCategory category) {
        this.taskCategoryRepository.save(category);
    }

    @Override
    public TaskCategory updateTaskCategory(TaskCategory category) {
        this.taskCategoryRepository.save(category);
        return this.getTaskCategoryById(category.getId());
    }

    @Override
    public boolean deleteTaskCategory(long id) {
        boolean answer = this.getTaskCategoryById(id) != null;
        this.taskCategoryRepository.delete(id);
        return answer;
    }

    @Override
    public void deleteAllCategories() {
        taskCategoryRepository.deleteAll();
    }
}
