import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProjectPosition } from '../models/project-position.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectPositionService {

  private url = 'http://localhost:8088/project-position/'; 
  private employeeUrl = 'http://localhost:8087/employee/'; 


  constructor(
    private http: HttpClient
  ) {}
 

  getById(id: number){
    return this.http.get(this.url + "?id=" + id );
  }

  getAllByEmployeeId(id: string): Observable<ProjectPosition[]>{
    return this.http.get<ProjectPosition[]>(this.url + "employee?id=" + id );
  }

  getAll() : Observable<ProjectPosition[]>{
    return this.http.get<ProjectPosition[]>(this.url + "all");
  }

  getActiveEmployees(){
    return this.http.get(this.employeeUrl + "active");
  }

  getEmployeeById(id: string){
    return this.http.get(this.employeeUrl + id);
  }

  createProjectPosition(body: ProjectPosition){
    return this.http.post(this.url, body)
  }

  startProjectPosition(id: number): Observable<string>{
    return this.http.get<string>(this.url + "start?id=" + id);
  }

  endProjectPosition(id: number): Observable<string>{
    return this.http.get<string>(this.url + "end?id=" + id);
  }
}
