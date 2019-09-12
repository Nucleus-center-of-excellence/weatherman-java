import { AppComponent } from './app.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RouteAuthService } from './services/route.auth.service';
import { LoginSignupComponent } from './login-signup/login-signup.component';

export const RouteDefinitions: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'verifyLink', component: ResetPasswordComponent, canActivate: [RouteAuthService]},
    {path: 'resetPassword', component: ResetPasswordComponent },
    {path: 'loginSignup', component: LoginSignupComponent },
    {path: '**', component: HomeComponent}
];
