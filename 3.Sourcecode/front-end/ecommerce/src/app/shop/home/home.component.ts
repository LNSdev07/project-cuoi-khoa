import { Component, OnInit } from '@angular/core';
import {Products} from "./home-model/home-response.model";
import {HomeServiceService} from "./home-service/home-service.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{
  slideConfig = {"slidesToShow": 1, "slidesToScroll": 1} ;

  products: any;
  constructor(private homeService: HomeServiceService) {
  }

  ngOnInit(): void {
    this.homeService.findProduct(this.getProductDetail()).subscribe(res =>{
      this.products = res.data;
      console.log(this.products)
    })
  }

  getProductDetail(): Products {
    const productDetail : Products = {
      productName: '',
      cost:0,
      imgAvt:''
    }
    return productDetail;
  }
}
