import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from './flight';
 
@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private _http: HttpClient) { }


  _urlFlights = 'http://localhost:8081/flights';


  _urlCreateFlight = 'http://localhost:8081/createFlight';


  _urlDeleteFlight = 'http://localhost:8081/deleteFlight/';



  getFlights(){

    return this._http.get<any>(this._urlFlights);

  }

  createFlight(flight : Flight){

    return this._http.post<any>(this._urlCreateFlight,flight);


  }

  deleteFlight(flightId : number){

    return this._http.delete<any>(this._urlDeleteFlight + flightId);


  }



}
