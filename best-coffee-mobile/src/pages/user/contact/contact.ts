import { Component } from '@angular/core';
import { NavController, App } from 'ionic-angular';
import { AuthService } from '../../../services/authentication.service';

@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {

  constructor(
    public navCtrl: NavController,
    private authService: AuthService,
    private app: App,
  ) {}

  logout(){
    this.authService.logout();
    this.app.getRootNav().popToRoot();
  }
}
