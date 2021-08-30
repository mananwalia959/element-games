import { Product } from './../../models/product';
import { ProductService } from './../../services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  products:Product[] = []

  constructor(private readonly productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().toPromise().then(products => this.products = products)
   }

}
