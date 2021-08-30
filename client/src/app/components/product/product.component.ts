import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  product: Product = {} as Product;

  constructor(private readonly route: ActivatedRoute, private readonly productService: ProductService) { }

  ngOnInit(): void {
    // add a null check here later
    const productid = this.route.snapshot.paramMap.get('id') || ''
    this.productService.getProduct(productid).toPromise().then(res => this.product = res);
  }

}
