import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { NetworkService } from './network.service';
import { url, routerPath } from '../shared/constant';
import { DialogService } from '../shared/dialog/dialog.service';
import { StorageService } from './storage.service';
import { GlobalService } from './global.service';


@Injectable({
  providedIn: 'root'
})
export class RouteAuthService implements CanActivate {
  loginDetails: any;
  constructor(  private networkService: NetworkService,
    private route: Router,
    private dialogService: DialogService,
    private storageService: StorageService,
    private globalService: GlobalService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean  {
    console.log('route', route);
    console.log('state', state);
    if (route.url.length > 0 && route.url[0].path === 'verifyLink') {
      this.verifyUser(route);
    } else {
     return true;
    }
  }
  verifyUser(route) {
    this.loginDetails = route.queryParams['userName'];
    console.log('before URIcomponent' , this.loginDetails);
    this.loginDetails = encodeURIComponent(this.loginDetails);
    console.log('after URIcomponent' , this.loginDetails);
    this.globalService.userDetails = this.loginDetails;
    this.storageService.write('userDetails', this.loginDetails);
    this.storageService.write('userDetails', this.loginDetails);
    this.networkService.getWithoutAuthentication(url.checkLinkExpiration + '?userName=' + this.loginDetails).subscribe(data => {
      // this.verified = true;
      this.route.navigate([routerPath.resetpassword]);
      return true;
    }, error => {
      const htmlContent = `<p>${error.error.message}`;
      const canNavigate = true;
      this.dialogService.errorDialogBox(htmlContent, canNavigate);
      return true;
    });
  }
}
