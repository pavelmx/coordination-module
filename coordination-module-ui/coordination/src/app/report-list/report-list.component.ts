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
  ) { }

  ngOnInit() {
    this.initFilter();
    this.initReportList();
    this.initProjectList();
    this.initEmployeeList();
  }

  initFilter(){
    console.log("init filter")
    this.filter.firstName = this.storage.getFirstName();
    this.filter.lastName = this.storage.getLastName();
    this.filter.adress = this.storage.getAdress();
    this.filter.phoneNumber = this.storage.getPhoneNumber();
    this.filter.skype = this.storage.getSkype();
    this.filter.email = this.storage.getEmail();
    this.filter.description = this.storage.getDescription();
    this.filter.active = this.storage.getActive();
    this.filter.departmentId = this.storage.getDepartment();
    this.filter.positionId = this.storage.getPosition();
  
  }

  resetFilter(){
    this.storage.init();
    this.initFilter();
    this.initReportList();
  }

  saveFilter(){    
    this.storage.setFirstName(this.filter.firstName);
    this.storage.setLastName(this.filter.lastName);
    this.storage.setAdress(this.filter.adress);
    this.storage.setPhoneNumber(this.filter.phoneNumber);
    this.storage.setSkype(this.filter.skype);
    this.storage.setEmail(this.filter.email);
    this.storage.setDescription(this.filter.description);
    this.storage.setActive(this.filter.active);
    this.storage.setDepartment(this.filter.departmentId);
    this.storage.setPosition(this.filter.positionId);
    console.log(this.storage.getPosition())  
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
    if (!this.filter.employee) {
      this.reportService.getAll()
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
    } else if (this.filter.employee) {
      this.reportService.getAllByEmployeeId(this.filter.employee)
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
    }
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
