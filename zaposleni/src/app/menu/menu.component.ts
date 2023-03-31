import { Component, OnInit} from '@angular/core';
import {ReservationsService} from '../reservations.service'
import {Reservation} from '../reservation'
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {



  reservations : Reservation[] = [];
  declineReservationId : number = 0;
  description : string = "";



  constructor(private _reservationsService : ReservationsService,private modalService: NgbModal) { }

  open(content : any, id : number) {

   this.declineReservationId  = id;
    this.modalService.open(content);
  }

  ngOnInit(): void {

    this._reservationsService.getNewReservations().subscribe((response) => {

      this.reservations = response;

    })


  }

    loadNewReservations(){

      this._reservationsService.getNewReservations().subscribe((response) => {

        this.reservations = response;
  
      })


    }

    loadAcceptedReservations(){

      this._reservationsService.getAcceptedReservations().subscribe((response) => {

        this.reservations = response;
  
      })

      
    }

    loadDeclinedReservations(){

      this._reservationsService.getDeclinedReservations().subscribe((response) => {

        this.reservations = response;
  
      })

      
    }

    acceptReservation(reservationId : number){

      this._reservationsService.acceptReservation(reservationId).subscribe((response) => {

        let status  = document.getElementById(reservationId.toString()) as HTMLInputElement;
        let acceptButton  = document.getElementById("acceptButton" + reservationId.toString()) as HTMLInputElement;
        let declineButton  = document.getElementById("declineButton" + reservationId.toString()) as HTMLInputElement;
        status.innerHTML = "prihvacena";
        acceptButton.hidden = true;
        declineButton.hidden = true;
  
      })


    }

   
    declineReservation(){


      this._reservationsService.declineReservation(this.declineReservationId, this.description).subscribe((response) => {

        this.description = "";

        let status  = document.getElementById(this.declineReservationId.toString()) as HTMLInputElement;
        let acceptButton  = document.getElementById("acceptButton" + this.declineReservationId.toString()) as HTMLInputElement;
        let declineButton  = document.getElementById("declineButton" + this.declineReservationId.toString()) as HTMLInputElement;
        status.innerHTML = "ponistena";
        acceptButton.hidden = true;
        declineButton.hidden = true;
  
      })



    }



}
