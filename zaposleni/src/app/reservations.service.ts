import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {


  _urlReservations = 'http://localhost:8081/reservations/';

  _urlAcceptReservation = 'http://localhost:8081/acceptReservation/';

  _urlDeclineReservation = 'http://localhost:8081/declineReservation/';


  constructor(private _http: HttpClient) { }

  getNewReservations(){

    return this._http.get<any>(this._urlReservations + "nova");

  }

  getAcceptedReservations(){

    return this._http.get<any>(this._urlReservations + "prihvacena");

  }

  getDeclinedReservations(){

    return this._http.get<any>(this._urlReservations + "ponistena");

  }


  acceptReservation(id : number){

    return this._http.get<any>(this._urlAcceptReservation + id);

  }
  declineReservation(id : number, description : string){

    return this._http.get<any>(this._urlDeclineReservation + id + '/' + description);

  }


}
