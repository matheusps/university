import { Component } from '@angular/core';
import { NavController, LoadingController, AlertController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { UnregisteredUser } from '../../../model/unregistered-user';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'page-register',
  templateUrl: 'register.html'
})
export class RegisterPage {

  name: string = "";
  username: string = "";
  email: string = "";
  password: string = "";

  constructor(
    public navCtrl: NavController,
    private loadingController: LoadingController,
    private alertController: AlertController,
    private userService: UserService
  ) {}

  register(){
    const loading = this.loading();
    const unregisteredUser = new UnregisteredUser(this.name, this.username, this.email, this.password);
    this.userService.register(unregisteredUser).subscribe(
      registeredUser => {
        loading.dismiss();
        this.alert("Done!", `User ${registeredUser.username} registered successfully!`);
        this.login();
      }
    );
  }

  login(){
    this.navCtrl.push(LoginPage);
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
