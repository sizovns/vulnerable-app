import { Component, OnInit } from '@angular/core';
import { CardResponse, CardService, CreateCardRequest } from '../_services/card.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create.card',
  templateUrl: './create.card.component.html',
  styleUrls: ['./create.card.component.css'],
})
export class CreateCardComponent {
  form: any = {
    name: null,
    number: null,
    holder: null,
    expDate: null,
    cvc: null
  };
  cards: Array<CardResponse> | undefined;

  constructor(private cardService: CardService, private router: Router) {}

  onSubmit(): void {
    const createCardRequest: CreateCardRequest = this.form

    this.cardService.createCard(createCardRequest).subscribe({
      next: data => {
        this.router.navigate(['/cards']);
      }
    });
  }
}
