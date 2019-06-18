import { Component, OnInit } from '@angular/core';
import { ProjectPositionService } from '../services/project-position.service';
import { ToastService } from '../services/toast.service';
import { ProjectPosition } from '../models/project-position.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-project-position-list',
  templateUrl: './project-position-list.component.html',
  styleUrls: ['./project-position-list.component.css']
})
export class ProjectPositionListComponent implements OnInit {

  list: ProjectPosition[];
  employeeId: string;

  constructor( 
    private projectPositionService: ProjectPositionService,   
    private toast: ToastService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.initProjectPositionList();
   
  }

  getEmployeeId(){
    this.cookieService.set('employeeId' , '10');
    this.employeeId = this.cookieService.get('employeeId');
  }

  initProjectPositionList() {  
    this.getEmployeeId() 
    this.projectPositionService.getAllByEmployeeId(this.employeeId)
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
