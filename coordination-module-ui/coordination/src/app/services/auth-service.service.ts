import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private checkUrl = 'http://localhost:8080/oauth/check_token';

  constructor(private http: HttpClient,
    private router: Router)  { }


  checkToken(token: string){
    const h = {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}    
    return this.http.post(this.checkUrl + "?token=" + token, h);
  }
 
  redirect(snapshot) {       
    if (snapshot.url.includes("#")) {
      var link = snapshot.url.split("#")[1];     
      this.router.navigate(["/" + link]);
    }
  }
}
