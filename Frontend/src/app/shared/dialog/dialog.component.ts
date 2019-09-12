import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  modalTitle: string;
  htmlContent: string;
  modalStyle: string;
  button1: string;
  button2: string;
  footer: string;
  constructor(public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
   ) {
    this.modalTitle = data.title;
    this.htmlContent = data.message;
    this.button1 = data.button1;
    this.button2 = data.button2;
    this.footer = data.footer;
   }

  ngOnInit() {
  }
  onYes() {
    this.dialogRef.close({result: 'yes'});
  }
  onNo() {
    this.dialogRef.close({result: 'no'});
  }

  closeDialogBox() {
    this.dialogRef.close({result: 'yes'});

  }
}
