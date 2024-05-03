import { Component, OnInit } from '@angular/core';
import { Product, ProductService } from '../_services/product.service';
import { BasketService } from '../_services/basket.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css'],
})
export class BasketComponent implements OnInit {
  basketId: string | undefined;
  product: Product | undefined;
  productMap: Map<Product, number> = new Map();
  sum: number = 0;

  constructor(
    private basketService: BasketService,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
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
              this.product = productById
              if (this.product) {
                this.sum = this.sum +  this.product.price * value;
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

  onButtonClicked(): void {
    next: this.basketService.clearBasket().subscribe();
    this.productMap = new Map();
    this.sum = 0;
  }

  onCreateOrder(): void {
    this.router.navigate(['/orders/create']);
  }
}
