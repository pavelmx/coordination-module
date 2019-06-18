import { Customer } from '../models/customer.model'

export class Project {
  
    id: number;
    name: string;
    model: string;
    code: string;
    customer: Customer;
    hoursForProject: number;
    startDate: Date;
    endDate: Date;
    plannedStartDate: Date;
    plannedEndDate: Date;
  }