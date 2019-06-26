import { Project } from '../models/project.model'

export class Report {
  
    id: number;
    project: Project;
    employeeId: string;
    hoursForTask: number;
    task: string;
    descriptionTask: string;
    reportDate: Date;
    reportType: string;
  }