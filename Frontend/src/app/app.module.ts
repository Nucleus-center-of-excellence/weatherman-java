
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER, ErrorHandler } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatDialogModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { AgmCoreModule, LAZY_MAPS_API_CONFIG, LazyMapsAPILoaderConfigLiteral } from '@agm/core';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { DialogComponent } from './shared/dialog/dialog.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCardModule } from '@angular/material/card';
import {
  MapModule,
} from 'angular-maps';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule, RouteReuseStrategy } from '@angular/router';
import { RouteDefinitions } from './app.routing';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { HomeComponent } from './home/home.component';
import { MatRadioModule } from '@angular/material/radio';
import { DatePipe } from '../../node_modules/@angular/common';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { map } from 'rxjs/operators';
import { BaseURL } from 'src/environments/environment';
import { url } from './shared/constant';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { CacheRouteReuseStrategy } from './services/cache-custom-route';
import { GlobalErrorHandler } from './services/GlobalErrorHandler';

export function agmConfigFactory(http: HttpClient, config: LazyMapsAPILoaderConfigLiteral) {
  return () => http.get<any>(BaseURL + url.getGMapKey).pipe(
    map(response => {
      config.apiKey = response.payload;
      return response;
    })
  ).toPromise();
}

@NgModule({
  declarations: [
    AppComponent,
    ResetPasswordComponent,
    DialogComponent,
    HomeComponent,
    LoginSignupComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [MatRadioModule,
    MatTooltipModule,
    MatInputModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatDialogModule,
    MatCardModule,
    MatIconModule,
    MatTabsModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(RouteDefinitions),
    MatButtonModule,
    ReactiveFormsModule,
    MapModule.forRoot(),
    AgmCoreModule.forRoot({
      // please get your own API key here:
      apiKey: '',
      libraries: ['places']
    }),
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production })
  ],
  providers: [
    // {
    //   provide: RouteReuseStrategy,
    //   useClass: CacheRouteReuseStrategy
    // },
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    {
      provide: APP_INITIALIZER,
      useFactory: agmConfigFactory,
      deps: [HttpClient, LAZY_MAPS_API_CONFIG],
      multi: true
    },
    DatePipe
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    DialogComponent,
  ],
})
export class AppModule {

}