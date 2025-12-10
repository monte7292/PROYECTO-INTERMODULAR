import { Component } from '@angular/core'
import { NgFor, NgIf } from '@angular/common'
import { HttpClient, HttpClientModule } from '@angular/common/http'
import { Router } from '@angular/router'
import { CartService } from '../../services/cart.service'

type Producto = { id?: number; nombre: string; marca: string; precio: number; image?: string }

@Component({
  selector: 'app-ratones',
  imports: [NgFor, HttpClientModule],
  templateUrl: './ratones.html',
  styleUrl: './ratones.css'
})
export class RatonesPage {
  ratones: Producto[] = []
  brands: string[] = []
  selectedBrands = new Set<string>()
  priceRanges = [
    { id: 'lt40', label: 'Menos de $40', min: 0, max: 40 },
    { id: '50_100', label: '$50 a $100', min: 50, max: 100 },
    { id: '100_150', label: '$100 a $150', min: 100, max: 150 },
    { id: 'gt150', label: 'Más de $150', min: 150, max: Infinity }
  ]
  selectedRanges = new Set<string>()
  pageSize = 6
  visible = 6

  constructor(private http: HttpClient, private router: Router, private cart: CartService) {}

  ngOnInit() {
    this.http.get<Producto[]>('http://localhost:8080/api/productos/tipo/Raton')
      .subscribe({
        next: (data) => {
          this.ratones = data
          const marcas = new Set<string>()
          for (const p of data) marcas.add(p.marca)
          this.brands = Array.from(marcas).sort()
        },
        error: () => { this.ratones = [] }
      })
  }

  toggleBrand(marca: string) {
    if (this.selectedBrands.has(marca)) this.selectedBrands.delete(marca)
    else this.selectedBrands.add(marca)
    this.resetPagination()
  }

  toggleRange(id: string) {
    if (this.selectedRanges.has(id)) this.selectedRanges.delete(id)
    else this.selectedRanges.add(id)
    this.resetPagination()
  }

  resetPagination() { this.visible = this.pageSize }
  loadMore() { this.visible = Math.min(this.visible + this.pageSize, this.filteredRatones.length) }

  get filteredRatones(): Producto[] {
    let list = this.ratones
    if (this.selectedBrands.size > 0) {
      list = list.filter(p => this.selectedBrands.has(p.marca))
    }
    if (this.selectedRanges.size > 0) {
      list = list.filter(p => {
        for (const id of this.selectedRanges) {
          const r = this.priceRanges.find(x => x.id === id)
          if (r && p.precio >= r.min && p.precio <= r.max) return true
        }
        return false
      })
    }
    return list
  }

  get visibleRatones() {
    return this.filteredRatones.slice(0, this.visible)
  }

  goToProduct(id?: number) { if (id != null) this.router.navigate(['/product', id]) }

  trackById(index: number, item: Producto) { return item.id ?? index }

  /* LIMPIAR FILTROS */
  clearFilters() {
    this.selectedBrands.clear()
    this.selectedRanges.clear()
    this.resetPagination()
  }

  addToCart(p: Producto) { this.cart.add(p, 1) }
}
