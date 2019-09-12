import { Injectable } from '@angular/core';
import { NetworkService } from '../services/network.service';
import { methodType, url } from '../shared/constant';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private networkService: NetworkService) { }

  login(data) {
    return this.networkService.request(methodType.post, url.login, data);
  }

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

  getLatLongFromCityName(cityName) {
    return this.networkService.request(methodType.getAccuweather, url.getLatLongFromCityName + cityName, null);
  }
  getActivationLink(email) {
    return this.networkService.request(methodType.get, url.forgotpassword + `?username=${email}`, null);
  }

  sendUserVote(data) {
    return this.networkService.request(methodType.post, url.postVote, data);
  }

  getFavProvider() {
    return this.networkService.request(methodType.get, url.getFavProvider, null);
  }

  getAvgOfProviders(lat, longi) {
    return this.networkService.request(methodType.get, url.getAvgOfProviders + "?lat=" + lat + "&longi=" + longi, null);
  }
}
