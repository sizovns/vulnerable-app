import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { USER_KEY } from './storage.service';
import { Observable } from 'rxjs';

const jsonString = sessionStorage.getItem(USER_KEY);
const jsonObject = JSON.parse(<string>jsonString);
const token = jsonObject?.token;

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  }),
};

@Injectable({
  providedIn: 'root',
})
export class BasketService {
  constructor(private http: HttpClient) {}

  addProductToBucket(productId: number, count: number): Observable<any> {
    return this.http.post(
      '/bff/baskets',
      { productId, count },
      httpOptions
    );
  }

  getBusket(): Observable<any> {
    return this.http.get('/bff/baskets', httpOptions);
  }

  clearBasket(): Observable<any> {
    return this.http.delete('/bff/baskets', httpOptions);
  }
}
