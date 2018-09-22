import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

//Components
import { AppComponent }  from './components/app.component';
import { NavbarComponent } from './components/navbar-component/navbar.component';
import { HomeComponent } from './components/home-component/home.component';
import { AboutComponent } from './components/about-component/about.component';
import { TaskListComponent } from './components/task-list-component/task-list.component';

//Services
import { TaskService } from './components/task-list-component/task.service';

const appRoutes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports:      [ BrowserModule, FormsModule, RouterModule.forRoot(appRoutes) ],
  declarations: [ AppComponent, NavbarComponent,TaskListComponent, HomeComponent, AboutComponent ],
  bootstrap:    [ AppComponent ],
  providers: [ TaskService ]
})

export class AppModule { }
