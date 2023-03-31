import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FlightLine} from './flight-line';

@Injectable({
  providedIn: 'root'
})
export class FlightlineService {

  constructor(private _http: HttpClient) { }


  _urlFlightLines = 'http://localhost:8081/flightLines';


  _urlTowns = 'http://localhost:8081/towns';


  _urlCreatePassengerFlightLine = "http://localhost:8081/createPassengerFlightLine";

  _urlCreateTransportFlightLine = "http://localhost:8081/createTransportFlightLine";


  _urlDeleteFlightLine = "http://localhost:8081/deleteFlightLine/";


  getFlightLines(){

    return this._http.get<any>(this._urlFlightLines);

  }


  getTowns(){

    return this._http.get<any>(this._urlTowns);

  }


    createPassengerFlightLine( flightLine : FlightLine){

      return this._http.post<any>(this._urlCreatePassengerFlightLine, flightLine);

    }


    createTransportFlightLine( flightLine : FlightLine){

      return this._http.post<any>(this._urlCreateTransportFlightLine, flightLine);

    }


    deleteFlightLine(idFlightLine : number){

      return this._http.delete<any>(this._urlDeleteFlightLine + idFlightLine);


    }


}
