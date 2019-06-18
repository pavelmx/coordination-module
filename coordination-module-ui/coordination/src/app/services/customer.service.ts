import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project.model';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private url = 'http://localhost:8088/customer/'; 

  constructor(
    private http: HttpClient
    ) {
  }
 

  getById(id: number){
    return this.http.get(this.url + "?id=" + id );
  }

  getAll() : Observable<Customer[]>{
    return this.http.get<Customer[]>(this.url + "all");
  }
}
