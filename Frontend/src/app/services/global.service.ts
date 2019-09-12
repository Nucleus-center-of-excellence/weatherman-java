import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  loginUserName: BehaviorSubject<string>;
  userDetails: any;
  constructor( private storageService: StorageService) {
    this.loginUserName = new BehaviorSubject<string>(null);
    this.userDetails = this.storageService.read('useDetails');
   }
}
