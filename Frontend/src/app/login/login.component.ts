import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginrequestModel } from '../shared/models/login.model';
import { GlobalService } from '../services/global.service';
import { DialogService } from '../shared/dialog/dialog.service';

import { StorageService } from '../services/storage.service';
import { UtilityService } from '../shared/utility.service';
import { Cookie } from 'ng2-cookies';
import { LoginService } from './login.service';
import { routerPath } from '../shared/constant';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  showForgotPassWord = false;
  htmlContent: any;
  onsubmitbtn = false;
  onsubmitbtnfpwd = false;
  forgotPasswordform: FormGroup;
  userName = 'null';
  loginrequestModel: LoginrequestModel;
  constructor(private formBuilder: FormBuilder,
    public globalService: GlobalService,
    private dialogService: DialogService,
    private router: Router,
    private loginService: LoginService,
    private storageService: StorageService,
    private utilityService: UtilityService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['',
        [Validators.required, Validators.maxLength(100),
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]],
      password: ['', Validators.required]
    });
    this.forgotPasswordform = this.formBuilder.group({
      email: ['',
        [Validators.required, Validators.maxLength(100),
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$')]],
    });
  }
  onSubmit(loginForm) {
    this.onsubmitbtn = true;
    console.log('###loginForm##', loginForm);
    if (loginForm.valid) {
      this.loginrequestModel = new LoginrequestModel();
      this.loginrequestModel.username = loginForm.value.email;
      this.loginrequestModel.password = loginForm.value.password;
      this.loginService.login(this.loginrequestModel).subscribe(data => {
        console.log(data, loginForm.value.email);
        this.globalService.loginUserName = loginForm.value.email;
        this.storageService.write('userToken', data.payload.token);
        this.storageService.write('userId', data.payload.userId);
        this.storageService.write('username', loginForm.value.email);
        //Setting the cookies for user logged in for 30 days.
        var expire = new Date();
        var time = Date.now() + ((1 * 60) * 1000); // current time + 30 mins ///
        expire.setTime(time);

        Cookie.set("TheWeatherManToken", data.payload.token, expire);
        Cookie.set("TheWeatherManUsername", loginForm.value.email, expire);

        this.htmlContent = `<p>Login Successfull.</p>`;
        this.dialogService.successDialogBox(this.htmlContent, true);
      }, error => {
        this.htmlContent = `<p>Invalid Credentials.</p>`;
        this.dialogService.errorDialogBox(this.htmlContent, true);
        this.utilityService.handleErrorResponse(error);
      });
    }
  }


  sendLink(forgotPasswordform) {
    this.onsubmitbtnfpwd = true;
    if (forgotPasswordform.valid) {
      this.loginService.getActivationLink(forgotPasswordform.value.email).subscribe(data => {
        console.log(data, forgotPasswordform.value.email);
        this.htmlContent = `<p>Activation link has been sent to your registered mail id.</p>`;
        this.dialogService.successDialogBox(this.htmlContent, true);
      }, error => {
        this.utilityService.handleErrorResponse(error);
      });
    }
  }

}
