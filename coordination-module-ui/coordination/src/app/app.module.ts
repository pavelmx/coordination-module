import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';import { CookieService } from 'ngx-cookie-service';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';import { NavbarComponent } from './navbar/navbar.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectPositionListComponent } from './project-position-list/project-position-list.component';
import { ProjectPositionService } from './services/project-position.service';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerService } from './services/customer.service';
import { ReportService } from './services/report.service';
import { ReportListComponent } from './report-list/report-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbdSortableHeader } from './sortable.directive';

@NgModule({
  declarations: [
    AppComponent,    
    HomeComponent,    
    NavbarComponent,
    ProjectListComponent,
    ProjectPositionListComponent,
    CustomerListComponent,
    ReportListComponent,
    NgbdSortableHeader
    
  ],
  imports: [
    NgbModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot({ timeOut: 5000, positionClass: 'toast-top-right'}),
    
  ],
  providers: [
    CookieService,
    ProjectPositionService, 
    CustomerService,
    ReportService],
  bootstrap: [AppComponent]
})
export class AppModule { }
