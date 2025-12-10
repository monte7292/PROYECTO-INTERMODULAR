import { Component } from '@angular/core'
import { NgIf } from '@angular/common'
import { FormsModule } from '@angular/forms'
import { Router, RouterLink } from '@angular/router'

@Component({
  selector: 'app-login',
  imports: [NgIf, FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginPage {
  email = ''
  password = ''
  showPass = false
  toast = false

  constructor(private router: Router) {}

  submit() {
    if (!this.email || !this.password) return
    this.toast = true
    setTimeout(() => { this.toast = false; this.router.navigate(['/']) }, 1500)
  }
}
