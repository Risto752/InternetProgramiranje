import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  _url = 'http://localhost:8081/login';

  constructor(private _http: HttpClient) { }

  login(user: User){

    return this._http.post<any>(this._url, user);

  }


}
