import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Url } from './url/url';
import { Observable } from '../../node_modules/rxjs';

@Injectable()
export class ApiService {
  API_URL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getUrls() : Observable<Url []>{
    return  this.http.get(`${this.API_URL}/urls`) as Observable<Url []>;
  }
 
  shortenUrl(url) {
    return  this.http.post(`${this.API_URL}/url`, url) as Observable<Url>;
  }

  originalUrl(url) {
    return  this.http.post(`${this.API_URL}/originalUrl`,url) as Observable<Url>;
  }

}
