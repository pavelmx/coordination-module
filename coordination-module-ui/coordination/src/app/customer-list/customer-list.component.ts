import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/customer.model';
import { ToastService } from '../services/toast.service';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  list: Customer[];

  constructor( private customerService: CustomerService,   
    private toast: ToastService) { }

  ngOnInit() {
    this.initCustomerList();
  }

  initCustomerList() {   
    this.customerService.getAll()
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
