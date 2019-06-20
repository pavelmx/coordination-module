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

  startProject(id: number): Observable<string>{
    return this.http.get<string>(this.url + "start?id=" + id);
  }

  endProject(id: number): Observable<string>{
    return this.http.get<string>(this.url + "end?id=" + id);
  }

  createProject(project: Project): Observable<Project>{
    return this.http.post<Project>(this.url, project);
  }
}
