import { Product } from './../models/product';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private readonly httpClient : HttpClient) {
   }

   public getProducts() {
     return this.httpClient.get<Product>("/api/products")
   }

}
