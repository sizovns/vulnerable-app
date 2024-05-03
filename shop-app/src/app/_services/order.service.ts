import { Injectable } from '@angular/core';
import { CardResponse } from './card.service';
import { Product } from './product.service';
import { USER_KEY } from './storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const jsonString = sessionStorage.getItem(USER_KEY);
const jsonObject = JSON.parse(<string>jsonString);
const token = jsonObject?.token;

export interface CreateOrderRequest {
  basketId: string;
  cardId: number;
}

export interface OrderResponse {
  id: number;
  paymentCardId: number;
  products: Array<Product>;
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  }),
};

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private http: HttpClient) {}

  getOrders(): Observable<any> {
    return this.http.get('/bff/orders', httpOptions);
  }

  getOrdersById(id: number): Observable<any> {
    return this.http.get(`/bff/orders/${id}`, httpOptions);
  }

  createOrder(createOrderRequest: CreateOrderRequest): Observable<OrderResponse> {
    return this.http.post<OrderResponse>('/bff/orders', createOrderRequest, httpOptions);
  }
}
