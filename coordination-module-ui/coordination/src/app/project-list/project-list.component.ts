import { Component, OnInit } from '@angular/core';
import { Project } from '../models/project.model';
import { ProjectService } from '../services/project.service';
import { ToastService } from '../services/toast.service';




@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  list: Project[];

  constructor( private projectService: ProjectService,   
    private toast: ToastService) { }

  ngOnInit() {
    this.initProjectList();
  }

  initProjectList() {   
    this.projectService.getAll()
      .subscribe(
        response => {
          this.list = response;
          console.log(response); 
        },
        error => {
          console.log(error);
        }
      );
  }
}
