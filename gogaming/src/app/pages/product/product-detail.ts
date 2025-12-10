import { Component, signal } from '@angular/core'
import { NgIf } from '@angular/common'
import { HttpClient, HttpClientModule } from '@angular/common/http'
import { ActivatedRoute } from '@angular/router'
import { CartService } from '../../services/cart.service'

@Component({
  selector: 'app-product-detail',
  imports: [NgIf, HttpClientModule],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.css'
})
export class ProductDetail {
  producto?: { id?: number; nombre: string; marca: string; precio: number; stock?: number; image?: string };
  cantidad = 1;
  showToast = signal(false);

  constructor(private route: ActivatedRoute, private http: HttpClient, private cart: CartService) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isFinite(id)) return;
    this.http.get<{ id?: number; nombre: string; marca: string; precio: number; stock?: number; image?: string }>(`http://localhost:8080/api/productos/${id}`)
      .subscribe({ next: (p) => this.producto = p });
  }

  comprar() {
    if (this.producto) this.cart.add(this.producto, Number(this.cantidad) || 1)
    this.showToast.set(true)
    setTimeout(() => this.showToast.set(false), 2500)
  }
}
