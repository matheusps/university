package com.matheus.lab3si.ws.model;

import javax.persistence.*;

/**
 * Created by Visitor on 1/24/2017.
 */
@Entity
public class Subtask {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "TASK")
    private Task task;

    private String title;
    private boolean isDone;

    public Subtask(){}

    public Subtask(Task task, String title) {
        this.task = task;
        this.title = title;
        this.isDone = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
