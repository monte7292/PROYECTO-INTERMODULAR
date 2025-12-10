import { Component } from '@angular/core'
import { NgFor, NgIf } from '@angular/common'
import { CartService } from '../../services/cart.service'

@Component({
  selector: 'app-cesta',
  imports: [NgFor, NgIf],
  templateUrl: './cesta.html',
  styleUrl: './cesta.css'
})
export class CestaPage {
  constructor(public cart: CartService) {}

  inc(id: number) { this.cart.updateQty(id, this.getQty(id) + 1) }
  dec(id: number) { this.cart.updateQty(id, this.getQty(id) - 1) }
  getQty(id: number) { return this.cart.items().find(i => i.id === id)?.qty ?? 1 }
  remove(id: number) { this.cart.remove(id) }
  comprar() { alert('Pedido realizado') }
}
