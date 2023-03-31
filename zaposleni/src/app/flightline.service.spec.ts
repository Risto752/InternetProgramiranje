import { TestBed } from '@angular/core/testing';

import { FlightlineService } from './flightline.service';

describe('FlightlineService', () => {
  let service: FlightlineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlightlineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
