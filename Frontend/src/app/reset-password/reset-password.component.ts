import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AddUserRequestModel } from '../shared/models/add-user.model';
import { DialogService } from '../shared/dialog/dialog.service';
import { ResetPasswordService } from './reset-password.service';
import { StorageService } from '../services/storage.service';
import { GlobalService } from '../services/global.service';
import { UtilityService } from '../shared/utility.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  resetForm: FormGroup;
  htmlContent: any;
  token: any;
  onsubmitbtn = false;
  addUserRequestModel: AddUserRequestModel;
  constructor(private formBuilder: FormBuilder,
    private dialogService: DialogService,
    private storageService: StorageService,
    private globalService: GlobalService,
    private utilityService: UtilityService,
    private resetPasswordService: ResetPasswordService) { }

  ngOnInit() {
    this.resetForm = this.formBuilder.group({
      newPassword: [''],
      confirmNewPassword: [''
      ]
    });
  }
  resetPassword(resetForm) {
    this.onsubmitbtn = true;
    if ( resetForm.valid) {
      if ( resetForm.value.newPassword ===  resetForm.value.confirmNewPassword) {
        this.token = this.globalService.userDetails ?
        this.globalService.userDetails : this.storageService.read('userDetails');
        this.addUserRequestModel = new  AddUserRequestModel();
        this.addUserRequestModel.password = resetForm.value.newPassword;
        this.resetPasswordService.saveData(this.addUserRequestModel, this.token).subscribe(data => {
          this.htmlContent = `<p>Password reset successfully.</p>`;
          this.dialogService.successDialogBox(this.htmlContent, true);
        }, error => {
          this.utilityService.handleErrorResponse(error);
        });
      } else {
        this.htmlContent = `<p>Your password should match with confirm password.</p>`;
        this.dialogService.errorDialogBox(this.htmlContent);
      }
    }

  }
}
