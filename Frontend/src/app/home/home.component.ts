import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { MakerModel } from '../shared/models/marker.model';
import { MatDialogRef, MatDialog } from '@angular/material';
import { GlobalService } from '../services/global.service';
import { StorageService } from '../services/storage.service';
import { MapsAPILoader } from '@agm/core';
import { Cookie } from 'ng2-cookies';
import { } from '@types/googlemaps';
import { AgmMap, LatLngBounds } from '@agm/core';
import { routerPath } from '../shared/constant';
import { Router } from '@angular/router';
import { DialogService } from '../shared/dialog/dialog.service';
import { LoginService } from '../login/login.service';
import { RecentLocationRequestModcel } from '../shared/models/recentLocation.model';
declare var google;
import { SwUpdate } from '@angular/service-worker';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  bgURL = "bg.png";
  toggleFahrenheit = false;
  showLogin = false;
  showLoginButton = true;
  weatherInfoAccu;
  weatherInfoDarkSky;
  weatherInfoOpenWeather;
  weatherInfoWeatherbit;
  weatherInfoUnlock;
  zoom = 8;
  lat: Number = 28.7041;
  lng: Number = 77.1025;
  username;
  cityName = '';
  cities;
  selectedProvider;
  feedbackSuccess;
  AvgProvider;
  FAV_PROVIDER_ID = 0;
  day = 0;
  selectedDay1;
  selectedDay2;
  selectedDay3;
  selectedDay4;
  date: Date;
  dates = [];
  @ViewChild('search')
  public searchElementRef: ElementRef;
  public searchControl: FormControl;
  markers: Array<MakerModel> = [];
  htmlContent: string;
  recentLocations: Array<RecentLocationRequestModcel> = [];
  recentLocation: RecentLocationRequestModcel;
  constructor(public dialog: MatDialog,
    private dialogService: DialogService,
    private datePipe: DatePipe,
    public globalService: GlobalService,
    private storageService: StorageService,
    private loginService: LoginService,
    private mapsAPILoader: MapsAPILoader,
    private route: Router,
    private ngZone: NgZone,
    private updates: SwUpdate) {
    updates.available.subscribe(event => {
      updates.activateUpdate().then(() => document.location.reload());
    });
  }
  ngOnInit() {
    let index = 0;
    while (index < 5) {
      this.date = new Date();
      this.dates.push(this.date.setDate(this.date.getDate() + index));
      index++;
    }
    this.getFavProvider();
    this.searchControl = new FormControl();
    if (this.storageService.read('recentLocations')) {
      this.recentLocations = this.storageService.read('recentLocations');
    }
    if (Cookie.get('TheWeatherManToken') && Cookie.get('TheWeatherManUsername')) {

      this.storageService.write('userToken', Cookie.get('TheWeatherManToken'));
      this.storageService.write('username', Cookie.get('TheWeatherManUsername'));
      this.username = this.storageService.read('username');
      this.showLogin = true;
      this.showLoginButton = false;
    } else {
      this.storageService.delete('username');
      this.storageService.delete('userToken');
    }
    this.setCurrentPosition();
    this.loadPlaceAutoComplete();
    this.selectedCity(this.lat, this.lng);
  }

  clearCache() {
    this.storageService.clearStorage();
    Cookie.deleteAll();
    this.showLogin = false;
    this.showLoginButton = true;
  }

  loadPlaceAutoComplete() {
    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ['geocode']

      });

      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          let place: google.maps.places.PlaceResult =
            autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          this.recentLocation = new RecentLocationRequestModcel();
          this.recentLocation.name = place.formatted_address ? place.formatted_address : '';
          this.recentLocation.lat = place.geometry.location.lat();
          this.recentLocation.lng = place.geometry.location.lng();
          this.recentLocations.push(this.recentLocation);
          this.storageService.write('recentLocations', this.recentLocations);
          this.setmarker(place.geometry.location.lat(), place.geometry.location.lng(), "additional info")
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();

          this.selectedCity(place.geometry.location.lat(), place.geometry.location.lng());
        });
      });
    });
  }

  setmarker(lat, lng, label) {
    this.markers = [];
    const mark = new MakerModel();
    mark.lat = lat;
    mark.lng = lng;
    mark.label = null;
    mark.draggable = true;
    this.markers.push(mark);

  }

  openLoginSignUp() {
    this.route.navigate([routerPath.loginSignUp]);
  }
  getLocation(location: RecentLocationRequestModcel) {
    this.searchControl.setValue(location.name);
    this.lat = location.lat;
    this.lng = location.lng;
    this.selectedCity(this.lat, this.lng);
    this.setmarker(this.lat, this.lng, 'initial');
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = location.lat;
        this.lng = location.lng;
        this.selectedCity(this.lat, this.lng);
        this.setmarker(this.lat, this.lng, 'initial');
      });
    }
  }

  selectedCity(lat, lng) {
    return new Promise((resolve, reject) => {
      this.loginService.getDarkskyData(lat, lng).subscribe(res => {
        if (res.payload) {
          this.weatherInfoDarkSky = res.payload.darksky;
        }
      }, error => {
        console.log(error);
      });

      this.loginService.getOpenweathermapData(lat, lng).subscribe(res => {
        if (res.payload) {
          this.weatherInfoOpenWeather = res.payload.openweathermap;
        }
      }, error => {
        console.log(error);
      });

      this.loginService.getWeatherbitData(lat, lng).subscribe(res => {
        if (res.payload) {
          this.weatherInfoWeatherbit = res.payload.weatherbit;
        }
      }, error => {
        console.log(error);
      });

      this.loginService.getWeatherunlockedData(lat, lng).subscribe(res => {
        if (res.payload) {
          this.weatherInfoUnlock = res.payload.weatherunlocked;
          this.getAvgOfProviders();
        }
      }, error => {
        console.log(error);
      });

    });


  }

  clickedMarker(label: string, index: number) {
  }
  public setCurrentPosition() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.selectedCity(this.lat, this.lng);
        this.setmarker(this.lat, this.lng, 'initial');
      });
    }
    this.setmarker(this.lat, this.lng, "initial");
  }


  twoDigits(d) {
    if (0 <= d && d < 10) return "0" + d.toString();
    if (-10 < d && d < 0) return "-0" + (-1 * d).toString();
    return d.toString();
  }

  radioChange(selectedProvider) {
    let json = {
      "userId": this.storageService.read("userId"),
      "providerId": selectedProvider
    }

    this.loginService.sendUserVote(json).subscribe(data => {
      if (data.status == 'OK') {
        this.htmlContent = `<p>${data.payload}</p>`;
        this.dialogService.successDialogBox(this.htmlContent, false);
      }

    })

  }

  getFavProvider() {
    this.loginService.getFavProvider().subscribe(data => {
      if (data.status == 'OK') {
        this.FAV_PROVIDER_ID = data.payload['FAV_PROVIDER_ID'];
      }
    });
  }

  getAvgOfProviders() {
    this.loginService.getAvgOfProviders(this.lat.toString(), this.lng.toString()).subscribe(data => {
      if (data.status == 'OK') {

        this.AvgProvider = data.payload;
        this.selectedDay(0);
      }
    });
  }

  selectedDay(day) {
    this.bgURL = this.AvgProvider[0].description+'.png';
    this.day = day;
    this.selectedDay1;
    this.selectedDay2;
    this.selectedDay3;
    this.selectedDay4;
    if (this.weatherInfoDarkSky.daily) {
      this.selectedDay1 = this.weatherInfoDarkSky.daily.data[day];
    }
    if (this.weatherInfoUnlock.Days) {
      this.selectedDay3 = this.weatherInfoUnlock.Days[day];
    }
    if (this.weatherInfoWeatherbit) {
      this.selectedDay2 = this.weatherInfoWeatherbit[day];
    }
    if (this.weatherInfoOpenWeather) {
      this.selectedDay4 = this.weatherInfoOpenWeather[day];
    }
  }
}
