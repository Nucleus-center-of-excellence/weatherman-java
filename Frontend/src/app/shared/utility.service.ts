import { Injectable } from '@angular/core';
import { DialogService } from './dialog/dialog.service';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  constructor( private dialogService: DialogService) { }
  handleErrorResponse(error, routeParameter?: string) {
    if (error.error != null) {
        if (error.error['code'] === 500) {
            const htmlContent = `<p>There is some issue in accessing. Please try again later.</p>`;
            this.dialogService.errorDialogBox(htmlContent);
        } else if (error.error['code'] === 401) {
            let htmlContent;
            if (error.error.message === 'Access Denied') {
                htmlContent = `<p>User session has been timed out</p>`;
            } else {
                htmlContent = `<p>${error.error.message}</p>`;
            }
            this.dialogService.errorDialogBox(htmlContent, false);
        } 

    } else if (error.status === 400) {
        const htmlContent = 'Please provide correct input.';
       this.dialogService.errorDialogBox(htmlContent);
    } 
}

}
