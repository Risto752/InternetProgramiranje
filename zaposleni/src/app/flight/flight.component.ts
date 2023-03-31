import { Component, OnInit } from '@angular/core';
import { Flight } from '../flight';
import {FlightService} from '../flight.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { FlightLine } from '../flight-line';
import {FlightlineService} from '../flightline.service';
import { NgForm } from '@angular/forms';
import {NgbDateStruct,NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import {NgbTimepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css'],
  providers : [NgbTimepickerConfig]
})
export class FlightComponent implements OnInit {

  flights : Flight[] = [];

  flightLines : FlightLine[] = [];


  flight : Flight = new Flight("","","","",0);

 
  constructor(private _flightService : FlightService, private modalService: NgbModal,private _flightLineService : FlightlineService,private calendar: NgbCalendar,config: NgbTimepickerConfig) { 
    config.seconds = true;
    config.spinners = false;
  }

  ngOnInit(): void {


    this._flightService.getFlights().subscribe((response) => {

      this.flights = response;

    })

    this._flightLineService.getFlightLines().subscribe((response) => {

      this.flightLines = response;

    })


  }

  open(content : any) {

    this.modalService.open(content);

  }

  createFlight(form : NgForm){

    this.flight.id = form.value.flightLine;

    this.flight.StartTime = form.value.takeOffDate.year + "-" + form.value.takeOffDate.month + "-" + form.value.takeOffDate.day + " " + form.value.takeOffTime.hour + ":" + form.value.takeOffTime.minute + ":" + form.value.takeOffTime.second;
    this.flight.EndTime = form.value.landingDate.year + "-" + form.value.landingDate.month + "-" + form.value.landingDate.day + " " + form.value.landingTime.hour + ":" + form.value.landingTime.minute + ":" + form.value.landingTime.second;

    this._flightService.createFlight(this.flight).subscribe((response) => {


      this._flightService.getFlights().subscribe((response) => {

        this.flights = response;
  
      })
  
     

    })
      
  }


  deleteFlight(flightId : number){


    this._flightService.deleteFlight(flightId).subscribe((response) => {

      this._flightService.getFlights().subscribe((response) => {

        this.flights = response;
  
      })

    })



  }

}
