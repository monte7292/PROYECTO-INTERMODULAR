import { Component, signal, computed } from '@angular/core'
import { NgIf } from '@angular/common'

@Component({
  selector: 'app-header',
  imports: [NgIf],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  menuOpen = signal(false)
  toggleMenu() { this.menuOpen.update(v => !v) }
  language = signal<'es' | 'en'>('es')
  flagEmoji = computed(() => this.language() === 'es' ? '🇪🇸' : '🇬🇧')
  ariaLabel = computed(() => this.language() === 'es' ? 'Idioma: Español' : 'Idioma: Inglés')
  toggleLanguage() { this.language.update(l => l === 'es' ? 'en' : 'es') }
  flagSrc = computed(() => this.language() === 'es' ? '/flags/es.svg' : '/flags/gb.svg')
}
