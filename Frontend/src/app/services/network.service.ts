import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { ResponseEntity } from '../shared/reponseEntity.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BaseURL } from '../../environments/environment';
import { catchError, tap } from 'rxjs/operators';
import { methodType } from '../shared/constant';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class NetworkService {

  constructor(private httpClient: HttpClient,
    private storageService: StorageService, ) { }

  /*
* This function makes an api call to post user login credentials.
* */
  public authentication(endPoint: string, data: any): Observable<ResponseEntity> {

    const userdetail = JSON.parse(JSON.stringify(data));
    // userdetail['password'] = window.btoa(data['password']);
    return this.httpClient.post<any>(BaseURL + endPoint, userdetail, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',

      })
    })
      .pipe(
        tap(
        ),
        catchError((error: any) => {
          return throwError(error);
        }
        )
      );
  }

  public request(typeOfMethod: methodType,
    endPoint: string,
    data: any,
  ): Observable<ResponseEntity> {
    if (typeOfMethod === 'POST') {
      return this.postRequest(endPoint, data);
    } else if (typeOfMethod === 'GET') {
      return this.getRequest(endPoint);
    }
    else if (typeOfMethod === 'GETAccuweather') {
      return this.getRequestForAccuweather(endPoint);
    }
    // if (typeOfMethod === 'DELETE') {
    // return this.deleteRequest(endPoint);
    // }
  }

  /**
    * This method makes an get request to the server.
    * @param url Url To append
    */
  private getRequest(endPoint: string): Observable<ResponseEntity> {
    const strBaseUrl: string = BaseURL;
    return this.httpClient.get<any>(BaseURL + endPoint, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Authorization': "Bearer " + this.storageService.read('userToken')
      })
    })
      .pipe(
        tap(
          data => {
            // if ( showLoader ) {
            // this.loader.displayLoader(false);
            // }
          }
        ),
        catchError((error: any) => {
          // if (showLoader) {

          //   this.loader.displayLoader(false);
          // }
          return throwError(error);
        }
        )
      );
  }
  private getRequestForAccuweather(endPoint: string): Observable<ResponseEntity> {
    const strBaseUrl: string = BaseURL;
    return this.httpClient.get<any>(endPoint, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Authorization': "Bearer " + this.storageService.read('userToken')
      })
    })
      .pipe(
        tap(
          data => {
            // if ( showLoader ) {
            // this.loader.displayLoader(false);
            // }
          }
        ),
        catchError((error: any) => {
          // if (showLoader) {

          //   this.loader.displayLoader(false);
          // }
          return throwError(error);
        }
        )
      );
  }
  private postRequest(endPoint: string, providerData: string): Observable<ResponseEntity> {
    let header;
    if (this.storageService.read('userToken')) {
      header = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.storageService.read('userToken')
      })
    } else {
      header = new HttpHeaders({
        'Content-Type': 'application/json',
      })
    }

    return this.httpClient.post<any>(BaseURL + endPoint, providerData, {
      headers: header
    })
      .pipe(
        tap(
          data => {
            // this.loader.displayLoader(false);
            console.log('success');
          }
        ),
        catchError((error: any) => {
          // this.loader.displayLoader(false);
          console.log(error);
          return throwError(error);
        }
        )
      );
  }

  getWithoutAuthentication(endPoint: string): Observable<ResponseEntity> {
    return this.httpClient.get<any>(BaseURL + endPoint, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    })
      .pipe(
        tap(
          data => {
            console.log('success');
          }
        ),
        catchError((error: any) => {
          return throwError(error);
        }
        )
      );
  }
}
