import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Project } from '../models/project.model';
import { ProjectService } from '../services/project.service';
import { ToastService } from '../services/toast.service';
import { NgForm } from '@angular/forms';
import { Customer } from '../models/customer.model';
import { CustomerService } from '../services/customer.service';




@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  list: Project[];
  customerList: Customer[];
  project: Project = new Project();
  customer: Customer = new Customer();

  @ViewChild('close_add') closeAddModal: ElementRef;
  @ViewChild('close_add_customer') closeAddCustomerModal: ElementRef;


  constructor( 
    private projectService: ProjectService,   
    private customerService: CustomerService, 
    private toast: ToastService) { }

  ngOnInit() {
    this.initProjectList();
    this.initCustomerList();
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
}