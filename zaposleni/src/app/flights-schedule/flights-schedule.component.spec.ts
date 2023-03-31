import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightsScheduleComponent } from './flights-schedule.component';

describe('FlightsScheduleComponent', () => {
  let component: FlightsScheduleComponent;
  let fixture: ComponentFixture<FlightsScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightsScheduleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightsScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
