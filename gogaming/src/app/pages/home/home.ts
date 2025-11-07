import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  @ViewChild('bestPricesContainer') bestPricesContainer?: ElementRef<HTMLDivElement>;

  scroll(direction: 'left' | 'right') {
    const el = this.bestPricesContainer?.nativeElement;
    if (!el) return;
    const delta = Math.round(el.clientWidth * 0.9) * (direction === 'left' ? -1 : 1);
    el.scrollBy({ left: delta, behavior: 'smooth' });
  }
}
