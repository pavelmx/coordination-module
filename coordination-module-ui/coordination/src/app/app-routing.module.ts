import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectPositionListComponent } from './project-position-list/project-position-list.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { ReportListComponent } from './report-list/report-list.component';

const routes: Routes = [
  
  {
    path: 'home',
    component: HomeComponent
  },  
  {
    path: 'report-list',
    component: ReportListComponent
  },
  {
    path: 'project-list',
    component: ProjectListComponent
  }, 
  {
    path: 'project-position-list',
    component: ProjectPositionListComponent
  },
  {
    path: 'customer-list',
    component: CustomerListComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
