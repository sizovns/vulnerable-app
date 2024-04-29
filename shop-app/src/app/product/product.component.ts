import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService, Product } from '../_services/product.service';
import { BasketService } from '../_services/basket.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  id: number;
  product: Product | undefined;
  productMap: Map<number, number> | undefined;

  constructor(
    private productService: ProductService,
    private basketService: BasketService,
    private route: ActivatedRoute
  ) {
    this.id = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.productService.getProduct(this.id).subscribe({
      next: (productData) => {
        this.product = productData;
        console.log(this.product);
      },
      error: (err) => {
        console.error('Error fetching user profile:', err);
      },
    });
  }

  onButtonClicked(): void {
    next: this.basketService.getBusket().subscribe({
      next: (baketResponse) => {
        console.log(baketResponse.products);
        this.productMap = new Map();
        if (baketResponse.prododucts) {
          this.productMap = baketResponse.products;
        }
        console.log(this.productMap);
        let count: number = 1;
        if (this.productMap) {
          const existingCount = this.productMap.get(this.id);
          if (typeof existingCount === 'number') {
            count = existingCount + 1;
          }
          this.basketService.addProductToBucket(this.id, count).subscribe();
        }
      },
    });
  }
}
