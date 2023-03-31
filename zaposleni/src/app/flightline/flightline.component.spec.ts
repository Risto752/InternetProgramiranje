import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightlineComponent } from './flightline.component';

describe('FlightlineComponent', () => {
  let component: FlightlineComponent;
  let fixture: ComponentFixture<FlightlineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightlineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightlineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
