import { Injectable } from '@angular/core';
import { NetworkService } from '../services/network.service';
import { methodType, url } from '../shared/constant';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor(private networkService: NetworkService) { }

  getAccuweatherData(lat, longi) {
    return this.networkService.request(methodType.get, url.getWeather + "/accuweather?lat=" + lat + "&" + "longi=" + longi, null);
  }

  getDarkskyData(lat, longi) {
    return this.networkService.request(methodType.get, url.getWeather + "/darksky?lat=" + lat + "&" + "longi=" + longi, null);
  }

  getOpenweathermapData(lat, longi) {
    return this.networkService.request(methodType.get, url.getWeather + "/openweathermap?lat=" + lat + "&" + "longi=" + longi, null);
  }

  getWeatherbitData(lat, longi) {
    return this.networkService.request(methodType.get, url.getWeather + "/weatherbit?lat=" + lat + "&" + "longi=" + longi, null);
  }

  getWeatherunlockedData(lat, longi) {
    return this.networkService.request(methodType.get, url.getWeather + "/weatherunlocked?lat=" + lat + "&" + "longi=" + longi, null);
  }

  sendUserVote(data) {
    return this.networkService.request(methodType.post, url.postVote, data);
  }

  getFavProvider() {
    return this.networkService.request(methodType.get, url.getFavProvider, null);
  }

  getAvgOfProviders(latLong) {
    return this.networkService.request(methodType.get, url.getAvgOfProviders + "?latLong="+latLong, null);
  }

}
