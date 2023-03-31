import { Component, OnInit } from '@angular/core';
import { FlightLine } from '../flight-line';
import { Town } from "../town";
import {FlightlineService} from '../flightline.service'
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router'


@Component({
  selector: 'app-flightline',
  templateUrl: './flightline.component.html',
  styleUrls: ['./flightline.component.css']
})
export class FlightlineComponent implements OnInit {


  flightLines : FlightLine[] = [];
  towns : Town[] = [];

  textBoxDisabled: boolean = true;

  labelDisabled: boolean = true;

  flightLineModel  = new FlightLine(1,"","","",0,"",0,true);

  flightType : string = "Putnicki prevoz";

  arrivalFlag : string = "Arrival";



 


  constructor(private _flightLineService : FlightlineService,private modalService: NgbModal, private router: Router) { }


    createFlightLine(form : NgForm){

       console.log(form.value);


      this.flightLineModel.arrivalFlag = form.value.flexRadio;
      this.flightLineModel.townId = form.value.town;
      this.flightLineModel.flightType = form.value.flexRadioDefault;
      this.flightLineModel.cargo_description = form.value.cargo_description;
      this.flightLineModel.seatCount = form.value.seat_count;

        if(this.flightLineModel.flightType === 'Putnicki prevoz'){


          this._flightLineService.createPassengerFlightLine(this.flightLineModel).subscribe((response) => {

            this._flightLineService.getFlightLines().subscribe((response) => {

              this.flightLines = response;
        
            })
          })

        }else{

          this._flightLineService.createTransportFlightLine(this.flightLineModel).subscribe((response) => {

            this._flightLineService.getFlightLines().subscribe((response) => {

              this.flightLines = response;
        
            })
          })
        }

    }


    deleteFlightLine(flightLineId : number){

      this._flightLineService.deleteFlightLine(flightLineId).subscribe((response) => {

        this._flightLineService.getFlightLines().subscribe((response) => {

          this.flightLines = response;
    
        })
      })


    }


  open(content : any) {

     this.modalService.open(content);

   }
 
   transportChosen(){
    this.textBoxDisabled = false;
  }

  passengerChosen(){
    this.textBoxDisabled = true;
  }

  arrivalChosen(){
    this.labelDisabled = true;
  }

  depatureChosen(){

    this.labelDisabled = false;
  }


  ngOnInit(): void {

    
    this._flightLineService.getFlightLines().subscribe((response) => {

      this.flightLines = response;

    })

    this._flightLineService.getTowns().subscribe((response) => {

      this.towns = response;

    })
    

  }

}
