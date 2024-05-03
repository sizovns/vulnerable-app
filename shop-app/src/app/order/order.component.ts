import { Component, OnInit } from '@angular/core';
import { OrderResponse, OrderService } from '../_services/order.service';
import { CardResponse, CardService } from '../_services/card.service';

interface OrdersForm {
  cardNumber: string | undefined;
  products: Array<string>;
  sum: number;
}

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  orders: Array<OrderResponse> | undefined;
  card: CardResponse | undefined;
  ordersForm: Array<OrdersForm> = new Array();

  constructor(
    private orderService: OrderService,
    private cardService: CardService // private basketService: BasketService,
  ) // private productService: ProductService,
  // private router: Router
  {}

  ngOnInit(): void {
    this.orderService.getOrders().subscribe({
      next: (ordersData) => {
        this.orders = ordersData;
        if (!this.orders) {
          return;
        }
        this.orders.forEach((order) => {
          let cardNumber: string | undefined;
          let sum: number = 0;
          let products: Array<string> = new Array();
          this.cardService.getCardById(order.paymentCardId).subscribe({
            next: (cardData) => {
              cardNumber = cardData.number;
              order.products.forEach((product) => {
                products.push(product.name);
                sum = sum + product.price;
              });
              const orderForm: OrdersForm = {
                cardNumber: cardNumber,
                products: products,
                sum: sum
              }
              this.ordersForm.push(orderForm)
            },
          });
        });
      },
      error: (err) => {
        console.error('Error fetching user profile:', err);
      },
    });
  }
}
