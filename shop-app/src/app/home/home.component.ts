import {Component, OnInit} from '@angular/core';
import {ProductService} from '../_services/product.service';


interface Product {
  id: number;
  name: string;
  price: number;
  imageUrl: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content: Product[] = []; // Define content as an array of Product

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (data: Product[]) => { // Specify the type of data as an array of Product
        // Iterate over the data and set it as a list
        this.content = data.map((item: Product) => { // Specify the type of item as Product
          return {
            id: item.id,
            name: item.name,
            price: item.price,
            imageUrl: item.imageUrl
          };
        });
      },
      error: err => {
        console.log(err);
        if (err.error) {
          console.log(JSON.parse(err.error).message);
        } else {
          console.log("Error with status: " + err.status);
        }
      }
    });
  }
}
