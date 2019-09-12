import { Injectable } from '@angular/core';
import { NetworkService } from '../services/network.service';
import { methodType, url } from '../shared/constant';

@Injectable({
  providedIn: 'root'
})
export class ResetPasswordService {

  constructor(private networkService: NetworkService) { }

  saveData(data, token) {
    return this.networkService.request(methodType.post, `${url.resetPassword}?userName=${token}`, data);
  }
}
