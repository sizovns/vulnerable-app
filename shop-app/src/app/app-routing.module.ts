import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

//import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import {ProductComponent} from "./product/product.component";
import {BasketComponent} from "./basket/basket.component";
import { CardsComponent } from './cards/cards.component';
import { CreateCardComponent } from './create.card/create.card.component';
import { CreateOrderComponent } from './create.order/create.order.component';
import { OrderComponent } from './order/order.component';
import { RegisterComponent } from './register/register.component';
//import { BoardUserComponent } from './board-user/board-user.component';
//import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
//import { BoardAdminComponent } from './board-admin/board-admin.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
 { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'basket', component: BasketComponent },
  { path: 'cards', component: CardsComponent },
  { path: 'cards/create', component: CreateCardComponent },
  { path: 'orders', component: OrderComponent },
  { path: 'orders/create', component: CreateOrderComponent },
//  { path: 'user', component: BoardUserComponent },
//  { path: 'mod', component: BoardModeratorComponent },
//  { path: 'admin', component: BoardAdminComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
