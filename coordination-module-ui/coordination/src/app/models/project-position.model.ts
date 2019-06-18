import { Project } from './project.model'
import { ProjectPositionEmployee } from './project-position-employee.model';

export class ProjectPosition {
  
    id: number;
    project: Project;
    projectPositionEmployee: ProjectPositionEmployee;    
    startDate: Date;
    endDate: Date;
    plannedStartDate: Date;
    plannedEndDate: Date;
  }