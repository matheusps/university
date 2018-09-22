import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { RegisterPage } from '../register/register';
import { AuthService } from '../../../services/authentication.service';
import { TabsPage } from '../../user/tabs/tabs';

@Component({
  selector: 'page-intro',
  templateUrl: 'intro.html'
})
export class IntroPage {

  constructor(
    public navCtrl: NavController,
    private authService: AuthService
  ) {
    if(this.authService.isAuthenticated()){
      this.navCtrl.setRoot(TabsPage);
    }
  }

  login(){
    this.navCtrl.push(LoginPage);
  }

  register(){
    this.navCtrl.push(RegisterPage);
  }
}
