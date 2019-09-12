import { Injectable } from '@angular/core';
import { NetworkService } from '../services/network.service';
import { methodType, url } from '../shared/constant';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {


  constructor(private networkService: NetworkService) { }

  saveData(data) {
    return this.networkService.request(methodType.post, url.addUser, data);
  }
}
