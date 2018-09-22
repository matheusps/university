/*
This component only shows the aplication name, and calls the TaskListComponent
*/
import { Component } from '@angular/core';
import { TaskListComponent } from '../task-list-component/task-list.component';

@Component({
  selector: 'app-home',
  templateUrl: 'app/components/home-component/home.component.html'
})

export class HomeComponent{
  appTitle = "Task List";
}
