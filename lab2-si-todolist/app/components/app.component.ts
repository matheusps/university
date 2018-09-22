/*
Main compoment of the app. This compoment serve as a router for all other
compoments
*/

import { Component } from '@angular/core';
import { NavbarComponent } from './navbar-component/navbar.component';

@Component({
  selector: 'my-app',
  template: `
  <router-outlet></router-outlet>
  <app-navbar></app-navbar>
  `
})

export class AppComponent  {}
