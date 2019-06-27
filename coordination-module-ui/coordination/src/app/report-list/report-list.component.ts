import { Component, OnInit } from '@angular/core';
import { Report } from '../models/report.model';
import { ReportService } from '../services/report.service';
import { ToastService } from '../services/toast.service';
import * as moment from 'moment';
import { ProjectService } from '../services/project.service';
import { Project } from '../models/project.model';
import { ProjectPositionService } from '../services/project-position.service';
import { CookieService } from 'ngx-cookie-service';
import { NgForm } from '@angular/forms';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.css']
})
export class ReportListComponent implements OnInit {

  list: Report[];
  projectList: Project[];
  newReport: Report = new Report();
  filter: any = {}
  statistics: any = {}
  reportBody: Report = new Report();
  isAdd: boolean = true;
  today: string;
  employeeList;

  constructor(
    private reportService: ReportService,
    private projectService: ProjectService,
    private projectPositionService: ProjectPositionService,
    private cookieService: CookieService,
    private toast: ToastService,
    private storage: StorageService
  ) { }

  ngOnInit() {
    this.initFilter();
    this.initReportList();
    this.initProjectList();
    this.initEmployeeList();
  }

  initFilter(){
    console.log("init filter")
    this.filter.descriptionTask = this.storage.getDescriptionTask();
    this.filter.employeeId = this.storage.getEmployeeId();
    this.filter.firstDate = this.storage.getFirstDate();
    this.filter.hoursForTask = this.storage.getHoursForTask();
    this.filter.lastDate = this.storage.getLastDate();
    this.filter.task = this.storage.getTask();
    this.filter.reportType = this.storage.getReportType();
    this.filter.projectId = this.storage.getProjectId();  
  }

  resetFilter(){
    this.storage.init();
    this.initFilter();
    this.initReportList();
  }

  saveFilter(){    
    this.storage.setDescriptionTask(this.filter.descriptionTask);
    this.storage.setEmployeeId(this.filter.employeeId);
    this.storage.setFirstDate(this.filter.firstDate);
    this.storage.setHoursForTask(this.filter.hoursForTask);
    this.storage.setLastDate(this.filter.lastDate);
    this.storage.setTask(this.filter.task);
    this.storage.setReportType(this.filter.reportType);
    this.storage.setProjectId(this.filter.projectId);    
    console.log(this.filter)
    this.initReportList();
  }

  initEmployeeList(){
    this.projectPositionService.getActiveEmployees()
      .subscribe(
        response => {
          this.employeeList = response;
          console.log(response);
        },
        error => {
          console.log(error);
        }
      );
  }

  initProjectList() {
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

  initReportList() {
    console.log(this.filter) 
      this.reportService.getAllFilter(this.filter)
        .subscribe(
          response => {
            this.list = response;
            console.log(response);
            this.getStatistics()
          },
          error => {
            console.log(error);
          }
        );
    // } else if (this.filter.employee) {
    //   this.reportService.getAllByEmployeeId(this.filter.employee)
    //     .subscribe(
    //       response => {
    //         this.list = response;
    //         console.log(response);
    //         this.getStatistics()
    //       },
    //       error => {
    //         console.log(error);
    //       }
    //     );
    // }
  }

  getEmployeeById() {
    var id = this.cookieService.get('employeeId'); //for users
    // var id = this.filter.employee.id; //for admin
    this.projectPositionService.getEmployeeById(id)
      .subscribe(
        response => {
          console.log(response)
          this.statistics.employee = response['personalInfo'].firstName + " " + response['personalInfo'].lastName;
        },
        error => {
          console.log(error)
        }
      );
  }

  getStatistics() {
    if (this.filter.employee) {
      this.getEmployeeById();
      var paidHours = '15';
      var workingHoursInMonth = '168';
      var workedHours = 0;
    } else if(!this.filter.employee){
      this.statistics.employee = 'all employees';
      var paidHours = '--';
      var workingHoursInMonth = '--';
      var workedHours = 0;
    }

    var daysOnVacation = 0;
    var daysOnSickLeave = 0;

    for (var i = 0; i < this.list.length; i++) {
      if (this.list[i].reportType == 'WORK') {
        workedHours += this.list[i].hoursForTask;
      }
      if (this.list[i].reportType == 'SICK_LEAVE') {
        daysOnSickLeave += 1;
      }
      if (this.list[i].reportType == 'VACATION') {
        daysOnVacation += 1;
      }
    }
    this.statistics.workedHours = workedHours;
    this.statistics.workingHoursInMonth = workingHoursInMonth;
    this.statistics.daysOnSickLeave = daysOnSickLeave;
    this.statistics.paidHours = paidHours;
    this.statistics.daysOnVacation = daysOnVacation;
  }

  getCurrentReport(report: Report) {
    this.isAdd = false;
    this.reportBody = report;
  }

  clearAddForm() {
    this.reportBody = new Report();
    this.isAdd = true;
  }

  compareFn(x: any, y: any): boolean {
    return x && y ? x == y : x == y;
  }

  compareFnId(x: any, y: any): boolean {
    return x && y ? x.id == y.id : x == y;
  }

  addEdit(form: NgForm) {
    var method = '';
    if (this.isAdd) {
      method = 'POST';
    } else {
      method = 'PUT';
    }
    var emp_id = this.cookieService.get('employeeId')
    this.reportBody.employeeId = emp_id;
    this.reportService.addEdit(this.reportBody, method)
      .subscribe(
        response => {
          console.log(response);
          this.getStatistics()
          form.resetForm();
          this.initReportList()
          this.toast.showSuccess('Success', 'Report updated success')
        },
        error => {
          console.log(error);
          this.toast.showSuccess('Error', error.error.message)
        }
      );
  }
}
