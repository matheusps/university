package com.matheus.lab3si.ws.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Visitor on 1/24/2017.
 */
@Entity
public class Task {

    @Id
    @Column(name = "TASK")
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    private boolean isDone;
    private String description;

    @ManyToOne
    @JoinColumn(name = "TASK_CATEGORY")
    private TaskCategory category;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Subtask> subtasks;

    @ManyToOne
    @JoinColumn(name = "TASK_LIST")
    private TaskList taskList;

    public Task(){}

    public Task(String title, boolean isDone, String description, TaskCategory category, TaskPriority priority, List<Subtask> subtasks) {
        this.title = title;
        this.isDone = isDone;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.subtasks = subtasks;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCategory getCategory() {
        this.category.setTasks(null);
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public TaskList getTaskList() {
        this.taskList.setTasks(null);
        return taskList;
    }
}
