import { Component, OnInit } from '@angular/core';
import { Report } from '../models/report.model';
import { ReportService } from '../services/report.service';
import { ToastService } from '../services/toast.service';


@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.css']
})
export class ReportListComponent implements OnInit {

  list: Report[];

  constructor( private reportService: ReportService,   
    private toast: ToastService) { }

  ngOnInit() {
    this.initCustomerList();
  }

  initCustomerList() {   
    this.reportService.getAll()
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
