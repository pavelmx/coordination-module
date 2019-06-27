import { Injectable } from '@angular/core';

const REPORT_TYPE = 'REPORT_TYPE';
const EMPLOYEE_ID = 'EMPLOYEE_ID';
const PROJECT_ID = 'PROJECT_ID';
const FIRST_DATE = 'FIRST_DATE';
const LAST_DATE = 'LAST_DATE';
const TASK = 'TASK';
const HOURS_FOR_TASK = 'HOURS_FOR_TASK';
const DESCRIPTION_TASK = 'DESCRIPTION_TASK';



@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { 
   this.init();
   }

  clearFilter() {
    sessionStorage.clear();
  }

  
  public init(){
    this.setReportType('');
    this.setEmployeeId('');
    this.setFirstDate('');
    this.setDescriptionTask('');
    this.setHoursForTask('');
    this.setLastDate('');
    this.setProjectId('');
    this.setTask('');     
  }

  public setReportType(field: string) {
    window.sessionStorage.removeItem(REPORT_TYPE);
    window.sessionStorage.setItem(REPORT_TYPE, field);
  }

  public getReportType(): string {
    return sessionStorage.getItem(REPORT_TYPE);
  }

  public setProjectId(field: string) {
    window.sessionStorage.removeItem(PROJECT_ID);
    window.sessionStorage.setItem(PROJECT_ID, field);
  }

  public getProjectId(): string {
    return sessionStorage.getItem(PROJECT_ID);
  }

  public setEmployeeId(field: string) {
    window.sessionStorage.removeItem(EMPLOYEE_ID);
    window.sessionStorage.setItem(EMPLOYEE_ID, field);
  }

  public getEmployeeId(): string {
    return sessionStorage.getItem(EMPLOYEE_ID);
  }

  public setFirstDate(field: string) {
    window.sessionStorage.removeItem(FIRST_DATE);
    window.sessionStorage.setItem(FIRST_DATE, field);
  }

  public getFirstDate(): string {
    return sessionStorage.getItem(FIRST_DATE);
  }

  public setLastDate(field: string) {
    window.sessionStorage.removeItem(LAST_DATE);
    window.sessionStorage.setItem(LAST_DATE, field);
  }

  public getLastDate(): string {
    return sessionStorage.getItem(LAST_DATE);
  }

  public setTask(field: string) {
    window.sessionStorage.removeItem(TASK);
    window.sessionStorage.setItem(TASK, field);
  }

  public getTask(): string {
    return sessionStorage.getItem(TASK);
  }

  public setHoursForTask(field: string) {
    window.sessionStorage.removeItem(HOURS_FOR_TASK);
    window.sessionStorage.setItem(HOURS_FOR_TASK, field);
  }

  public getHoursForTask(): string {
    return sessionStorage.getItem(HOURS_FOR_TASK);
  }

  public setDescriptionTask(field: string) {
    window.sessionStorage.removeItem(DESCRIPTION_TASK);
    window.sessionStorage.setItem(DESCRIPTION_TASK, field);
  }

  public getDescriptionTask(): string {
    return sessionStorage.getItem(DESCRIPTION_TASK);
  }

}