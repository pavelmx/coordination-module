import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  [x: string]: any;

  private url = 'http://localhost:8088/project/'; 
  private urlPPE = 'http://localhost:8088/project-position-employee/'; 

  constructor(
    private http: HttpClient
    ) {
  }
 

  getById(id: number){
    return this.http.get(this.url + "?id=" + id );
  }

  getAllByCustomerId(id: number){
    return this.http.get(this.url + "customer?id=" + id );
  }

  getAll() : Observable<Project[]>{
    return this.http.get<Project[]>(this.url + "all");
  }

  getPage(page: number, size: number, column: string, order: string) : Observable<Project[]>{
    return this.http.get<Project[]>(this.url + "page?size=" + size + "&page=" 
    + page + "&column=" + column + "&order=" + order);
  }

  startProject(id: number): Observable<string>{
    return this.http.get<string>(this.url + "start?id=" + id);
  }

  endProject(id: number): Observable<string>{
    return this.http.get<string>(this.url + "end?id=" + id);
  }

  createProject(project: Project): Observable<Project>{
    return this.http.post<Project>(this.url, project);
  }

  editProject(project: Project){
    return this.http.put<Project>(this.url, project);
  }

  getAllActive() : Observable<Project[]>{
    return this.http.get<Project[]>(this.url + "active");
  }

  getEmployeesByProject(id_project){
    console.log(id_project)
    return this.http.get(this.urlPPE + "project?id_project=" + id_project);
  }
}
