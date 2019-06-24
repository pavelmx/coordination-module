import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Report } from '../models/report.model';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private url = 'http://localhost:8088/report/'; 

  constructor(
    private http: HttpClient
    ) {
  }
 
  getById(id: number){
    return this.http.get(this.url + "?id=" + id );
  }

  getAll() : Observable<Report[]>{
    return this.http.get<Report[]>(this.url + "all");
  }

  add(body: Report): Observable<Report>{
    return this.http.post<Report>(this.url, body);
  }
  
}
