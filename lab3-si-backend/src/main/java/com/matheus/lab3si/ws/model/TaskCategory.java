package com.matheus.lab3si.ws.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Visitor on 1/24/2017.
 */
@Entity
public class TaskCategory {

    @Id
    @Column(name = "TASK_CATEGORY")
    @GeneratedValue
    private long id;
    private String title;

    @OneToMany(targetEntity = Task.class, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public TaskCategory(){}

    public TaskCategory(String title) {
        this.title = title;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
