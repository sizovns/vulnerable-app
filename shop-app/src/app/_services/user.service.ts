import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { USER_KEY } from './storage.service';

const API_URL = 'http://localhost:8080/api/test/';

const jsonString = sessionStorage.getItem(USER_KEY);
const jsonObject = JSON.parse(<string>jsonString);
const token = jsonObject?.token;


const httpOptions = {
  headers: new HttpHeaders({    
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
    }),
};


@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getMyInfo(): Observable<any> {
    return this.http.get('/api/users/me',  httpOptions);
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }
  
  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
}
