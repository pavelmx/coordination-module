import { Component, OnInit, ViewChild, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { ProjectPositionService } from '../services/project-position.service';
import { ToastService } from '../services/toast.service';
import { ProjectPosition } from '../models/project-position.model';
import { CookieService } from 'ngx-cookie-service';
import { NgForm } from '@angular/forms';
import { ProjectPositionEmployee } from '../models/project-position-employee.model';
import { Project } from '../models/project.model';
import { ProjectService } from '../services/project.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { NgbdSortableHeader, SortEvent } from '../sortable.directive';

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
  projectPositionEmployee: any = {}
  page: number = 0;
  size: number = 10;
  column: string = 'id';
  order: string = 'asc';
  length: number;
  lastPage: number;

  @ViewChild('close_add', {static: false}) closeAddModal: ElementRef;

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  
  constructor( 
    private projectPositionService: ProjectPositionService,  
    private projectService: ProjectService,    
    private toast: ToastService,
    private cookieService: CookieService) { }

//////////////////////////////////////////////////
    searchProject = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 1 ? []
        : this.projectList.filter(v => v.name.toLowerCase().indexOf(term.toLowerCase()) > -1).splice(0, 10))
    )
    formatterProject = (x : Project) => x.name;

    searchEmployee = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 1 ? []
        : this.activeEmployeeList.filter((data) => this.matchValue(data.personalInfo, term)).splice(0, 10))
        )
    formatterEmployee = (x : {id: number, personalInfo : {firstName: string, lastName: string}}) => x.personalInfo.firstName + " " + x.personalInfo.lastName;
    matchValue(data, value) {
      return Object.keys(data).map((key) => {
         return new RegExp(value, 'gi').test(data[key]);
      }).some(result => result);
    }
    ////////////////////////////////////////////  

    onPageChange(pageNumber){   
      this.page = pageNumber-1;   
      this.initProjectPositionList(); 
      
    }

    onSort({column, direction}: SortEvent) {
      this.headers.forEach(header => {
        if (header.sortable !== column) {
          header.direction = '';
        }
      });
      this.page = 0;
      this.column = column;
      this.order = direction;      
      this.initProjectPositionList();
    }

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
    this.projectPositionService.getPageByEmployeeId(this.employeeId, this.page, this.size, this.column, this.order)
      .subscribe(
        response => {
          this.list = response['content'];
          this.length = response['totalElements'];
          this.lastPage = response['totalPages']; 
          console.log(response); 
        },
        error => {
          console.log(error);
        }
      );
  }

  getProjectList(){
    this.projectService.getAllActive()
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
    this.projectPosition.projectPositionEmployee = this.projectPositionEmployee;
    this.projectPosition.projectPositionEmployee.employeeId = this.projectPositionEmployee.employeeId.id;
    console.log(this.projectPosition)
    console.log(this.projectPositionEmployee)
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
