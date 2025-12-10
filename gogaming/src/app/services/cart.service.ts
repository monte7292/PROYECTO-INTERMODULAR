import { Injectable, effect, signal, inject } from '@angular/core'
import { isPlatformBrowser } from '@angular/common'
import { PLATFORM_ID } from '@angular/core'

export type CartItem = { id: number; nombre: string; marca: string; precio: number; image?: string; qty: number }

@Injectable({ providedIn: 'root' })
export class CartService {
  items = signal<CartItem[]>([])
  private isBrowser = isPlatformBrowser(inject(PLATFORM_ID))

  constructor() {
    const raw = this.isBrowser ? localStorage.getItem('gg_cart') : null
    if (raw) {
      try { this.items.set(JSON.parse(raw)) } catch { this.items.set([]) }
    }
    effect(() => {
      if (!this.isBrowser) return
      localStorage.setItem('gg_cart', JSON.stringify(this.items()))
    })
  }

  add(item: { id?: number; nombre: string; marca: string; precio: number; image?: string }, qty = 1) {
    if (item.id == null) return
    const list = this.items()
    const idx = list.findIndex(i => i.id === item.id)
    if (idx >= 0) {
      const next = [...list]
      next[idx] = { ...next[idx], qty: next[idx].qty + qty }
      this.items.set(next)
    } else {
      this.items.set([...list, { id: item.id, nombre: item.nombre, marca: item.marca, precio: item.precio, image: item.image, qty }])
    }
  }

  updateQty(id: number, qty: number) {
    const list = this.items().map(i => i.id === id ? { ...i, qty: Math.max(1, qty) } : i)
    this.items.set(list)
  }

  remove(id: number) { this.items.set(this.items().filter(i => i.id !== id)) }
  clear() { this.items.set([]) }
  total() { return this.items().reduce((sum, i) => sum + i.precio * i.qty, 0) }
  count() { return this.items().reduce((sum, i) => sum + i.qty, 0) }
}
