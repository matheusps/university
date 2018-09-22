package com.matheus.lab3si.ws.service.implementation;

import com.matheus.lab3si.ws.model.Subtask;
import com.matheus.lab3si.ws.repository.ISubtaskRepository;
import com.matheus.lab3si.ws.service.ISubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by matheus on 25/01/17.
 */
@Service
public class SubtaskService implements ISubtaskService {

    @Autowired
    private ISubtaskRepository subtaskRepository;

    @Override
    public Collection<Subtask> getAllSubtasks() {
        return this.subtaskRepository.findAll();
    }

    @Override
    public Subtask getSubtaskById(long id) {
        return this.subtaskRepository.findOne(id);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        this.subtaskRepository.save(subtask);
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        this.subtaskRepository.save(subtask);
        return this.getSubtaskById(subtask.getId());
    }

    @Override
    public boolean deleteSubtask(long id) {
        boolean answer = this.getSubtaskById(id) != null;
        this.subtaskRepository.delete(id);
        return answer;
    }

    @Override
    public void deleteAllSubtasks() {
        subtaskRepository.deleteAll();
    }
}
