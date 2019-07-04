import { Component, OnInit, ViewChild, ElementRef, ViewChildren, QueryList } from '@angular/core';
import { Project } from '../models/project.model';
import { ProjectService } from '../services/project.service';
import { ToastService } from '../services/toast.service';
import { NgForm } from '@angular/forms';
import { Customer } from '../models/customer.model';
import { CustomerService } from '../services/customer.service';

import {Observable} from 'rxjs';
import {NgbTypeaheadConfig} from '@ng-bootstrap/ng-bootstrap';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import { NgbdSortableHeader, SortEvent } from '../sortable.directive';



@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
  providers: [NgbTypeaheadConfig] 
})
export class ProjectListComponent implements OnInit {

  list: Project[];
  customerList: Customer[];
  employeeList;
  project: Project = new Project();
  customer: Customer = new Customer();
  model: any;
  page: number = 0;
  size: number = 10;
  column: string = 'id';
  order: string = 'asc';
  length: number;
  lastPage: number;

  @ViewChild('close_add', {static: false}) closeAddModal: ElementRef;
  @ViewChild('close_add_customer', {static: false}) closeAddCustomerModal: ElementRef;
  @ViewChild('info', {static: false}) infoModal: ElementRef;
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;


  constructor( 
    private projectService: ProjectService,   
    private customerService: CustomerService, 
    private toast: ToastService) { }

    search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term.length < 1 ? []
        : this.customerList.filter(v => v.name.toLowerCase().indexOf(term.toLowerCase()) > -1).splice(0, 10))
    )
    formatter = (x : Project) => x.name;
    

    onPageChange(pageNumber){   
      this.page = pageNumber-1;   
      this.initProjectList(); 
      
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
      this.initProjectList();
    }

  ngOnInit() {
    this.initProjectList();
    this.initCustomerList();
  }

  initProjectList() {   
    this.projectService.getPage(this.page, this.size, this.column, this.order)
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

  initEmployeeListByProject(id_project) {   
    this.projectService.getEmployeesByProject(id_project)
      .subscribe(
        response => {
          this.employeeList = response;
          console.log(response)
        },
        error => {
          console.log(error);
        }
      );
  }

  initCustomerList(){
    this.customerService.getAll()
      .subscribe(
        response => {
          this.customerList = response;
          console.log(response); 
        },
        error => {
          console.log(error);
        }
      );
  }

  createCustomer(form: NgForm){
    console.log(this.customer)
    this.customerService.createCustomer(this.customer)
    .subscribe(
      response => {
        console.log(response)
        this.initCustomerList();
        this.closeAddCustomerModal.nativeElement.click();
        form.resetForm();
        this.toast.showInfo("", "Customer '" + response.name + "' created success")
      },
      error => {
        console.log(error)
      }
    );
  }

  newProject(){
    this.project = new Project();
  }

  editProject(){
    console.log(this.project)
    this.projectService.editProject(this.project)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectList();
        this.infoModal.nativeElement.click();
        this.toast.showInfo("", "Project '" + response.name + "' updated success")
      },
      error => {
        this.toast.showError("Error", error.error.message); 
        console.log(error)
      }
    );
  }

  createProject(form: NgForm){
    console.log(this.project)
    this.projectService.createProject(this.project)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectList();
        this.closeAddModal.nativeElement.click();
        form.resetForm();
        this.toast.showInfo("", "Project '" + response.name + "' created success")
      },
      error => {
        console.log(error)
      }
    );
  }

  startProject(id: number){
    this.projectService.startProject(id)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectList();
        this.toast.showInfo("", response['response'])
      },
      error => {
        console.log(error)
      }
    );
  }

  endProject(id: number){
    this.projectService.endProject(id)
    .subscribe(
      response => {
        console.log(response)
        this.initProjectList();
        this.toast.showInfo("", response['response'])
      },
      error => {
        console.log(error)
      }
    );
  }

  infoAboutProject(project: Project){
    this.projectService.getEmployeesByProject(project.id)
    .subscribe(
      response => {
        console.log(response)
        this.employeeList = response;
      }, 
      error => {
        console.log(error)
      }
    );
    this.project = project;
    this.initProjectList();
  }
}