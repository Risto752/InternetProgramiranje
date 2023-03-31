import { Component, OnInit } from '@angular/core';
import {LoginService} from '../login.service'
import { User } from '../user';
import { Router } from '@angular/router'

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {


  userModel = new User("", "");

  constructor(private _loginService : LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser(){


      this._loginService.login(this.userModel)
      .subscribe(
        data => {

            if(data === true){

            this.router.navigate(["/menu"])

            }else{

              document.getElementById("loginError")?.setAttribute("style", "display:block")

            }

        },
        error => console.log("Error!", error),

      )
      

  }


}
