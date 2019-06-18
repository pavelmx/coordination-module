import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectPositionListComponent } from './project-position-list.component';

describe('ProjectPositionListComponent', () => {
  let component: ProjectPositionListComponent;
  let fixture: ComponentFixture<ProjectPositionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectPositionListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectPositionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
