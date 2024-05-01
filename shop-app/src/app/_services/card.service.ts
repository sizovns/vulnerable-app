import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { USER_KEY } from './storage.service';

const jsonString = sessionStorage.getItem(USER_KEY);
const jsonObject = JSON.parse(<string>jsonString);
const token = jsonObject?.token;

export interface CreateCardRequest {
  name: string;
  number: number;
  holder: string;
  expDate: Date;
  cvc: string;
}

export interface UpdateCardRequest {
  id: number;
  name: string;
  number: number;
  holder: string;
  expDate: Date;
  cvc: string;
}

export interface CardResponse {
  id: number;
  name: string;
  number: number;
  holder: string;
  expDate: Date;
  cvc: string;
}

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
    }),
};


@Injectable({
  providedIn: 'root'
})
export class CardService {

  constructor(private http: HttpClient) {}


  getMyCards(): Observable<any> {
    return this.http.get('/bff/cards/my',  httpOptions);
  }

  getCardById(id: number): Observable<any> {
    return this.http.get(`/bff/cards?cardId=${id}`,  httpOptions);
  }


  createCard(createCardRequest: CreateCardRequest): Observable<any> {
    return this.http.post('/api/cards', createCardRequest, httpOptions);
  }

  updateCard(updateCardRequest: UpdateCardRequest): Observable<any> {
    return this.http.put('/api/cards', updateCardRequest, httpOptions);
  }

  deleteCard(id: number): Observable<any> {
    return this.http.delete(`/api/cards/${id}`, httpOptions);
  }

}
