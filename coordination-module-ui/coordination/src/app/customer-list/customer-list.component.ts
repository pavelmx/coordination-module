import { Component, OnInit, ViewChildren, QueryList, ViewChild, ElementRef } from '@angular/core';
import { Customer } from '../models/customer.model';
import { ToastService } from '../services/toast.service';
import { CustomerService } from '../services/customer.service';
import { SortEvent, NgbdSortableHeader } from '../sortable.directive';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customerEdit: Customer = new Customer();
  list: Customer[];
  page: number = 0;
  size: number = 10;
  column: string = 'id';
  order: string = 'asc';
  length: number;
  lastPage: number;

  @ViewChild('close', {static: false}) closeModal: ElementRef;
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  constructor( private customerService: CustomerService,   
    private toast: ToastService) { }

    onPageChange(pageNumber){   
      this.page = pageNumber-1;   
      this.initCustomerList(); 
      
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
      this.initCustomerList();
    }

  ngOnInit() {
    this.initCustomerList();
  }

  initCustomerList() {   
    this.customerService.getPage(this.page, this.size, this.column, this.order)
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

  setEditCustomer(customer: Customer){
    this.customerEdit = customer;
    this.initCustomerList();
  }

  edit(form: NgForm){
    this.customerService.editCustomer(this.customerEdit)
      .subscribe(
        response => {         
          console.log(response);
          this.initCustomerList();
          this.closeModal.nativeElement.click();
          form.resetForm();
          this.toast.showSuccess("", "Customer " + this.customerEdit.name + " updated success"); 
        },
        error => {
          this.toast.showError("Error", error.error.message); 
          console.log(error);
        }
      );
  }
}
