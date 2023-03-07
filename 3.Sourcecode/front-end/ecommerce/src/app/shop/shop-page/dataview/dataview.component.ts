import { Component } from '@angular/core';

import {PrimeNGConfig, SelectItem} from "primeng/api";
import {ShopServiceService} from "../shop-service/shop-service.service";
import {ProductsResponse} from "../shop-model/shop-response.model";
import {PageRequest} from "../../../admin/common/page-request.model";


@Component({
  selector: 'app-dataview',
  templateUrl: './dataview.component.html',
  styleUrls: ['./dataview.component.scss']
})
export class DataviewComponent {
//   product: ProductsResponse ={
//   productName : '',
//   shortDescription : '' ,
//   cost: 0,
//   imgAvt: ''
// };

  products: any;
  sortOptions!: SelectItem[];

  sortOrder!: number;

  sortField!: string;
  sortKey: any;

  paginator: PageRequest={
    page: 1,
    pageSize: 8,
    sortBy: '',
    condition: ''
  }
  totalRecords!: number;
  dv: any;
  constructor( private primengConfig: PrimeNGConfig,
               private shopService: ShopServiceService) { }

  ngOnInit() {

    this.shopService.findProductForShop(this.paginator).subscribe(res =>{

      this.products = res.data;
      this.totalRecords = res.totalRecords;
      console.log(res)
      console.log(this.products);
    })
    this.primengConfig.ripple = true;
  }

  onSortChange(event:any) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }
}
