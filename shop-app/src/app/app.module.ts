import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';

import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { ProductComponent } from './product/product.component';
import { BasketComponent } from './basket/basket.component';
import { CardsComponent } from './cards/cards.component';
import { CreateCardComponent } from './create.card/create.card.component';
import { CreateOrderComponent } from './create.order/create.order.component';
import { OrderComponent } from './order/order.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    ProductComponent,
    BasketComponent,
    CardsComponent,
    CreateCardComponent,
    CreateOrderComponent,
    OrderComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
