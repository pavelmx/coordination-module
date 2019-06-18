import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service.service';
import { Router, RouterStateSnapshot } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  link: string;
  accessToken: string;

  constructor(
    private authService: AuthServiceService,
    private router: Router,    
    private cookieService: CookieService
  ) { }

  ngOnInit() {   
   this.accessToken = this.cookieService.get("access_token");
    this.authService.checkToken(this.accessToken)
    .subscribe(
      response => {      
        console.log(response);
        const snapshot: RouterStateSnapshot = this.router.routerState.snapshot; 
        this.authService.redirect(snapshot);
      },
      error => {      
        console.log(error); 
        //window.location.href = "http://localhost:8080";
      }
    );  
  }

  
  

}
