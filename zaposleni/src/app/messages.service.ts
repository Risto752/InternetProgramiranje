import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message } from './message';


@Injectable({
  providedIn: 'root'
})
export class MessagesService {


  _urlMessages = 'http://localhost:8081/messages';

  _urlMessageSeen = 'http://localhost:8081//messageSeen/';

  _urlFilterMessage = 'http://localhost:8081//messages/';

  _urlEmail = 'http://localhost:8081/email';



  constructor(private _http: HttpClient) { }


  getMessages(){

    return this._http.get<any>(this._urlMessages);

  }

  messageSeen(messageId : number){

    return this._http.get<any>(this._urlMessageSeen + messageId);


  }

  filterMessage(content : string){

    return this._http.get<any>(this._urlFilterMessage + content);

  }


  answerMessage(message : any){


    return this._http.post<any>(this._urlEmail, message);


  }



}
