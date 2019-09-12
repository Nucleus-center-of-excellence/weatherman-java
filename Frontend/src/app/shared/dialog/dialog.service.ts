import { Injectable } from '@angular/core';
import { DialogComponent } from './dialog.component';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { routerPath } from '../constant';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(public dialog: MatDialog,
    private router: Router) { }
  /**
   * This function is called to diaplay dialog box with success message
   * @param message
   */
  successDialogBox(message, loginRoute?: boolean, otherPage?: string): Promise<boolean>  {
    const dialogRef = this.dialog.open(DialogComponent, {
      height: 'auto',
      width: '300px',
      data: {
        title: 'The Weather Man', message: message,
        button1: 'Ok'
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (loginRoute) {
        this.router.navigate([routerPath.home], { replaceUrl: true });
      }
      // if (otherPage) {
      //   this.router.navigate([otherPage]);
      // }
    });
    return dialogRef.afterClosed().toPromise();
  }

  /**
   * This function is called to diaplay dialog box with error message
   * @param message
   */
  errorDialogBox(message, route?: boolean, otherPage?: string) {
    const dialogRef = this.dialog.open(DialogComponent, {
      height: 'auto',
      width: '300px',
      data: {
        title: 'Alert', message: message,
        button1: 'Ok'
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      // if (route) {
      //   this.router.navigate([routerPath.home]);
      // }
      // if (otherPage) {
      //   this.router.navigate([otherPage]);
      // }
      console.log('The dialog was closed', result);
    });
  }
}
