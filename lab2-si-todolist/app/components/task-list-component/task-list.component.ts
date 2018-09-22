/*
This component do all the dirty work on handling tasks
*/
import { Component} from '@angular/core';
import { Task } from './task';
import { TaskService } from './task.service';

import { OnInit } from '@angular/core';

@Component({
  selector: 'task-list',
  templateUrl: 'app/components/task-list-component/task-list.component.html',
  providers: [ TaskService ]
})

export class TaskListComponent implements OnInit{

  newTaskTitle: string;
  tasks: Task[];

  constructor(private taskService: TaskService){
    this.newTaskTitle = "";
  }

  ngOnInit(): void{
    this.getTasks();
  }

  getTasks(): void{
    this.tasks = this.taskService.getTasks();
  }

  resetNewTaskTitle(): void{
    this.newTaskTitle = "";
  }

  insertSelectedClass(task: Task): string[]{
    if(task.isDone){
      return ['selected-item'];
    }
  }

  getNumberOfTasks(): number{
    return this.tasks.length;
  }

  getNumberOfDoneTasks(): number{
    let count = 0;
    this.tasks.forEach((item, index) => {
      if(item.isDone){
        count  = count + 1;
      }
    });
    return count;
  }

  getTaskPercentage(): number{
    let percentage = 0;
    let totalTasks = this.getNumberOfTasks();
    let doneTasks = this.getNumberOfDoneTasks();

    percentage = (doneTasks/totalTasks) * 100;
    //round number
    percentage = +percentage.toFixed(2);
    // fix NaN
    return (percentage)? percentage : 0;
  }

  addTask(): void{
    let newTask = new Task(this.newTaskTitle);
    this.tasks.push(newTask);
    this.resetNewTaskTitle();
  }

  removeTask(task: Task): void{
    let index = this.tasks.indexOf(task);
    this.tasks.splice(index, 1);
  }

}
