import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgFor } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-home',
  imports: [NgFor, HttpClientModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
  
}
