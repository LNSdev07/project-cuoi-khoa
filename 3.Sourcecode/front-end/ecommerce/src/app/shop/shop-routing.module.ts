import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {CartComponent} from './cart/cart.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {ProductSingleComponent} from './product-single/product-single.component';
import {ProfileDetailsComponent} from './profile-details/profile-details.component';
import {ErrorComponent} from './error/error.component';
import { ShopComponent } from './shop.component';
import {ContactComponent} from "./contact/contact.component";


const routes: Routes = [{path: '', component: HomeComponent},
  {path:'shop', component: ShopComponent},
  {path: 'cart', component: CartComponent},
  {path: 'check-out', component: CheckoutComponent},
  {path: 'orders', component: CheckoutComponent},
  {path: 'product-single', component: ProductSingleComponent},
  {path: 'profile-detail', component: ProfileDetailsComponent},
  {path:'contact',component: ContactComponent},
  {path: '**', component: ErrorComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }
