import { Component, OnInit } from '@angular/core';
import {
  CardResponse,
  CardService
} from '../_services/card.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-card',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css'],
})
export class CardsComponent implements OnInit {
  cards: Array<CardResponse> | undefined;

  constructor(
    private cardService: CardService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getCards();
  }

  onButtonClicked(id: number) {
    this.cardService.deleteCard(id).subscribe();
    this.getCards();
  }

  getCards(): void {
    this.cardService.getMyCards().subscribe({
      next: (cardData: Array<CardResponse>) => {
        this.cards = cardData;
      },
    });
  }
}
