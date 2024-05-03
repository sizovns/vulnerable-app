import { Component, OnInit } from '@angular/core';
import { CardResponse, CardService } from '../_services/card.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product, ProductService } from '../_services/product.service';
import { BasketService } from '../_services/basket.service';
import { CreateOrderRequest, OrderService } from '../_services/order.service';

@Component({
  selector: 'app-create.order',
  templateUrl: './create.order.component.html',
  styleUrls: ['./create.order.component.css'],
})
export class CreateOrderComponent implements OnInit {
  basketId: string | undefined;
  cardId: number | undefined;
  product: Product | undefined;
  cards: Array<CardResponse> | undefined;
  productMap: Map<Product, number> = new Map();
  sum: number = 0;

  constructor(
    private orderService: OrderService,
    private cardService: CardService,
    private basketService: BasketService,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCards();
    this.sum = 0;
    this.basketService.getBusket().subscribe({
      next: (productData) => {
        this.basketId = productData.id;
        const tempMap: Map<number, number> = productData.products.reduce(
          (acc: { [key: number]: number }, current: Product) => {
            const amount = acc[current.id] ?? 0;
            acc[current.id] = amount + 1;
            return acc;
          },
          {}
        );

        Object.entries(tempMap).map(([key, value]) => {
          this.productService.getProduct(Number(key)).subscribe({
            next: (productById) => {
              this.product = productById;
              if (this.product) {
                this.sum = this.sum + this.product.price * value;
                this.productMap.set(this.product, value);
              }
            },
          });
        });
      },
      error: (err) => {
        console.error('Error fetching basket:', err);
      },
    });
  }

  getCards(): void {
    this.cardService.getMyCards().subscribe({
      next: (cardData: Array<CardResponse>) => {
        this.cards = cardData;
      },
    });
  }

  onItemChange(value: any) {
    this.cardId = value.target.id;
    console.log(' Card id is : ', this.cardId);
  }

  onButtonClicked() {
    if (!this.cardId || !this.basketId || this.productMap.size == 0) {
      console.log('Not possible to order')
      return;
    }
    const createOrderRequest: CreateOrderRequest = {
      cardId: this.cardId,
      basketId: this.basketId
    }

    console.log(createOrderRequest);

    this.orderService.createOrder(createOrderRequest).subscribe();
    this.basketService.clearBasket().subscribe();
    this.router.navigate(['/orders']);
  }
}
