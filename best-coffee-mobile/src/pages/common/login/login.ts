import { Component } from '@angular/core';
import { NavController, LoadingController, AlertController } from 'ionic-angular';
import { RegisterPage } from '../register/register';
import { AuthService } from '../../../services/authentication.service';
import { UserCredentials } from '../../../model/user-credentials';
import { TabsPage } from '../../user/tabs/tabs';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  username: string = "";
  password: string = "";

  constructor(
    public navCtrl: NavController,
    private loadingController: LoadingController,
    private alertController: AlertController,
    private authService: AuthService,
  ) {}

  login(){
    const loading = this.loading();
    const credentials = new UserCredentials(this.username, this.password);
    this.authService.login(credentials).subscribe(
      authenticatedUser => {
        loading.dismiss();
        this.passAuth();
      },
      err => {
        loading.dismiss();
        this.alert("Error", "The user/password combination is invalid!");
      }
    );
  }

  register(){
    this.navCtrl.push(RegisterPage);
  }

  passAuth(){
    this.navCtrl.push(TabsPage);
  }

  loading(){
    let loading = this.loadingController.create({
      content: 'Por favor, aguarde...',
      spinner: 'bubbles'
    });
    loading.present();
    return loading;
  }

  alert(title, message) {
    let alert = this.alertController.create({
      title: title,
      subTitle: message,
      buttons: ['OK']
    });
    alert.present();
  }
}
