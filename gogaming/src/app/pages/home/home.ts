import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgFor } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-home',
  imports: [NgFor, HttpClientModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  @ViewChild('bestPricesContainer') bestPricesContainer?: ElementRef<HTMLDivElement>;
  ratones: Array<{ id?: number; nombre: string; marca: string; precio: number; image?: string }> = [];
  startIndex = 0;
  animating = false;
  lastDirection: 'left' | 'right' = 'right';

  /* OBTENER RATONES CON APIS */
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.http.get<Array<{ id?: number; nombre: string; marca: string; precio: number; image?: string }>>('http://localhost:8080/api/productos/tipo/Raton')
      .subscribe({
        next: (data) => this.ratones = data,
        error: () => this.ratones = []
      });
  }

  /* ESTILOS Y ANIMACIONES CSS */
  scroll(direction: 'left' | 'right') {
    const el = this.bestPricesContainer?.nativeElement;
    if (!el) return;
    const delta = Math.round(el.clientWidth * 0.9) * (direction === 'left' ? -1 : 1);
    el.scrollBy({ left: delta, behavior: 'smooth' });
  }
  trackById(index: number, item: { id?: number }) { return item.id ?? index }
  get visibleRatones() {
    if (!this.ratones || this.ratones.length <= 4) return this.ratones;
    const res = [] as Array<{ id?: number; nombre: string; marca: string; precio: number; image?: string }>;
    for (let i = 0; i < 4; i++) {
      res.push(this.ratones[(this.startIndex + i) % this.ratones.length]);
    }
    return res;
  }
  showNext() {
    if (this.ratones.length > 0) {
      this.lastDirection = 'right';
      this.animating = true;
      setTimeout(() => {
        this.startIndex = (this.startIndex + 1) % this.ratones.length;
        this.animating = false;
      }, 250);
    }
  }
  showPrev() {
    if (this.ratones.length > 0) {
      this.lastDirection = 'left';
      this.animating = true;
      setTimeout(() => {
        this.startIndex = (this.startIndex - 1 + this.ratones.length) % this.ratones.length;
        this.animating = false;
      }, 250);
    }
  }
}
