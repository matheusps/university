package com.matheus.lab3si.ws.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Visitor on 1/24/2017.
 */
@Entity
public class TaskList {

    @Id
    @Column(name = "TASK_LIST")
    @GeneratedValue
    private long id;

    private String title;

    @OneToMany(targetEntity = Task.class, mappedBy = "taskList", cascade = CascadeType.ALL)
    private Collection<Task> tasks;

    public TaskList(){}

    public TaskList(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }
}
