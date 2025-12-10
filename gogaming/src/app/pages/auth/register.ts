import { Component } from '@angular/core'
import { NgIf } from '@angular/common'
import { RouterLink } from '@angular/router'
import { FormsModule } from '@angular/forms'
import { Router } from '@angular/router'

@Component({
  selector: 'app-register',
  imports: [NgIf, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterPage {
  nombre = ''
  email = ''
  password = ''
  confirm = ''
  accept = false
  marketing = false
  showPass = false
  showConfirm = false
  toast = false
  error = ''

  constructor(private router: Router) {}

  submit() {
    if (!this.nombre || !this.email || !this.password || !this.confirm) { this.error = 'Completa todos los campos'; return }
    if (this.password !== this.confirm) { this.error = 'Las contraseñas no coinciden'; return }
    if (!this.accept) { this.error = 'Debes aceptar la política de privacidad'; return }
    this.error = ''
    this.toast = true
    setTimeout(() => { this.toast = false; this.router.navigate(['/login']) }, 2000)
  }
}
