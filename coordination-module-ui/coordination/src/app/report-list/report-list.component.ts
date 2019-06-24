import { Component, OnInit } from '@angular/core';
import { Report } from '../models/report.model';
import { ReportService } from '../services/report.service';
import { ToastService } from '../services/toast.service';
import * as moment from 'moment';
import { ProjectService } from '../services/project.service';
import { Project } from '../models/project.model';
import { ProjectPositionService } from '../services/project-position.service';
import { CookieService } from 'ngx-cookie-service';

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
  //  {workedHours: number, paidHours:number, daysOnVacation: number,
  //    daysOnSickLeave: number, employee: string, workingHoursInMonth: number};


  constructor(
    private reportService: ReportService,
    private projectService: ProjectService,
    private projectPositionService: ProjectPositionService,
    private cookieService: CookieService,
    private toast: ToastService,
  ) { }

  ngOnInit() {
    this.initReportList();
    this.initProjectList();
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
  }

  getEmployeeById() {
    this.projectPositionService.getEmployeeById(this.cookieService.get('employeeId'))
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
    this.getEmployeeById();
    var paidHours = 15;
    var workedHours = 0;
    var daysOnVacation = 0;
    var daysOnSickLeave = 0;
    var workingHoursInMonth = 168;
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


  addReport() {
    console.log(this.newReport)
    this.reportService.add(this.newReport)
      .subscribe(
        response => {
          console.log(response)
          this.initReportList()
        },
        error => {
          console.log(error)
        }
      );
  }

  getValuesFromCells() {
    var table: HTMLTableElement = <HTMLTableElement>document.getElementById("table");
    var rows = table.rows.length;
    var columns = table.rows[0].cells.length;
    var task = table.rows[rows - 1].cells[columns - 3].innerHTML;
    this.newReport.task = task;
    console.log(task + "=" + this.newReport.task)
    var descriptionTask = table.rows[rows - 1].cells[columns - 2].innerHTML;
    this.newReport.descriptionTask = descriptionTask;
    console.log(descriptionTask + "=" + this.newReport.descriptionTask)
    var hoursForTask = table.rows[rows - 1].cells[columns - 1].innerHTML;
    this.newReport.hoursForTask = parseFloat(hoursForTask);
    console.log(hoursForTask + "=" + this.newReport.hoursForTask)
    this.addReport()
  }
}
