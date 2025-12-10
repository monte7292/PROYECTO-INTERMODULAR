import { Routes } from '@angular/router'
import { Home } from './pages/home/home'
import {About} from "./pages/about/about";
import { ProductDetail } from './pages/product/product-detail';
import { RatonesPage } from './pages/ratones/ratones';
import { CestaPage } from './pages/cesta/cesta';
import { LoginPage } from './pages/auth/login';
import { RegisterPage } from './pages/auth/register';

export const routes: Routes = [
  { path: '', component: Home },
    { path: 'about', component: About },
    { path: 'product/:id', component: ProductDetail },
    { path: 'ratones', component: RatonesPage },
    { path: 'cesta', component: CestaPage },
    { path: 'login', component: LoginPage },
    { path: 'register', component: RegisterPage },
]
