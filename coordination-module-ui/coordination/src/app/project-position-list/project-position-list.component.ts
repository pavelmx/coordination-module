import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ProjectPositionService } from '../services/project-position.service';
import { ToastService } from '../services/toast.service';
import { ProjectPosition } from '../models/project-position.model';
import { CookieService } from 'ngx-cookie-service';
import { NgForm } from '@angular/forms';
import { ProjectPositionEmployee } from '../models/project-position-employee.model';
import { Project } from '../models/project.model';
import { ProjectService } from '../services/project.service';

@Component({
  selector: 'app-project-position-list',
  templateUrl: './project-position-list.component.html',
  styleUrls: ['./project-position-list.component.css']
})
export class ProjectPositionListComponent implements OnInit {

  list: any[];
  activeEmployeeList;
  employee: any;
  employeeId: string;
  projectList: Project[];
  projectPosition: ProjectPosition = new ProjectPosition();
  projectPositionEmployee: ProjectPositionEmployee = new ProjectPositionEmployee();

  @ViewChild('close_add') closeAddModal: ElementRef;

  
  constructor( 
    private projectPositionService: ProjectPositionService,  
    private projectService: ProjectService,    
    private toast: ToastService,
    private cookieService: CookieService) { }

  ngOnInit() {
    this.initProjectPositionList();
    this.getProjectList();
    this.getEmployeeList();
  }

  getEmployeeId(){
    this.cookieService.set('employeeId' , '1');
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

  getProjectList(){
    this.projectService.getAll()
    .subscribe(
      response => {
        this.projectList = response;
        console.log(response); 
      },
      error => {
        console.log(error);
      }
    );
  }

  getEmployeeList(){
    this.projectPositionService.getActiveEmployees()
    .subscribe(
      response => {
        this.activeEmployeeList = response;
        console.log(response); 
      },
      error => {
        console.log(error);
      }
    );
  }

  createProjectPosition(form: NgForm){
    console.log(this.projectPosition)
    console.log(this.projectPositionEmployee)
    this.projectPosition.projectPositionEmployee = this.projectPositionEmployee;
    this.projectPositionService.createProjectPosition(this.projectPosition)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectPositionList();
        this.closeAddModal.nativeElement.click();
        form.resetForm();
        this.toast.showInfo("", "You will start work on '" + response['projectPositionEmployee'].position + "' position")
      },
      error => {
        console.log(error)
      }
    );
  }

  startProjectPosition(id: number){
    this.projectPositionService.startProjectPosition(id)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectPositionList();
        this.toast.showInfo("", response['response'])
      },
      error => {
        console.log(error)
      }
    );
  }

  endProjectPosition(id: number){
    this.projectPositionService.endProjectPosition(id)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectPositionList();
        this.toast.showInfo("", response['response'])
      },
      error => {
        console.log(error)
      }
    );
}
}
