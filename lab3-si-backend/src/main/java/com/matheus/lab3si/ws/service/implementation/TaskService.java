package com.matheus.lab3si.ws.service.implementation;

import com.matheus.lab3si.ws.model.Task;
import com.matheus.lab3si.ws.repository.ITaskRepository;
import com.matheus.lab3si.ws.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by matheus on 25/01/17.
 */
@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public Collection<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return this.taskRepository.findOne(id);
    }

    @Override
    public void createTask(Task task) {
        this.taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        this.taskRepository.save(task);
        return this.getTaskById(task.getId());
    }

    @Override
    public boolean deleteTask(long id) {
        boolean answer = this.getTaskById(id) != null;
        this.taskRepository.delete(id);
        return answer;
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
