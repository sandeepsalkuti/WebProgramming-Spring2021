import { Component } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  //  styleUrls: ['./header.component.css']
  styles: [
    '.background {background: crimson; color: black;font-size:20px}',
    'li a { color: black;font-size:17px}',
    'ul.nav a:hover { color: #fffccc ;font-size:17px }'
  ]
})
export class HeaderComponent {
  constructor() {}

  }
