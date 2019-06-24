import { Project } from '../models/project.model'

export class Report {
  
    id: number;
    project: Project;
    employeeId: number;
    hoursForTask: number;
    task: string;
    descriptionTask: string;
    reportDate: Date;
    reportType: string;
  }