package com.matheus.lab3si.ws.service.implementation;

import com.matheus.lab3si.ws.model.TaskList;
import com.matheus.lab3si.ws.repository.ITaskListRepository;
import com.matheus.lab3si.ws.service.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by matheus on 25/01/17.
 */
@Service
public class TaskListService implements ITaskListService {

    @Autowired
    private ITaskListRepository taskListRepository;

    @Override
    public Collection<TaskList> getAllTaskLists() {
        return this.taskListRepository.findAll();
    }

    @Override
    public TaskList getTaskListById(long id) {
        return this.taskListRepository.findOne(id);
    }

    @Override
    public void createTaskList(TaskList taskList) {
        this.taskListRepository.save(taskList);
    }

    @Override
    public TaskList updateTaskList(TaskList taskList) {
        this.taskListRepository.save(taskList);
        return this.getTaskListById(taskList.getId());
    }

    @Override
    public boolean deleteTaskList(long id) {
        boolean answer = this.getTaskListById(id) != null;
        this.taskListRepository.delete(id);
        return answer;
    }

    @Override
    public void deleteAllTaskLists() {
        taskListRepository.deleteAll();
    }
}
