import { Component, OnInit } from '@angular/core';
import { Message } from '../message'
import {MessagesService} from '../messages.service'
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  messages : Message[] = [];



  message : string  = "";
  email : string = "";
  title : string = "";
  answearTextArea : string = "";

  

  constructor(private _messagesService : MessagesService,private modalService: NgbModal) { }

  open(content : any,id : number, message : string, email : string, title : string) {

    this.message = message;
    this.email = email;
    this.title = title;
    

     this.modalService.open(content);

       this._messagesService.messageSeen(id).subscribe((response) => {

        let unboldMessage  = document.getElementById("message" + id.toString()) as HTMLInputElement;
       unboldMessage.style.fontWeight = "normal";
  
      })



   }

   answearMessage(){

   var answer = {

    message : this.answearTextArea,
    email : this.email,
    title : this.title

   }
    

    this._messagesService.answerMessage(answer).subscribe((response) => {


      this.answearTextArea = "";


    })


   }



   filterMessages(event : any){

     let searchValue = event.target.value;

     if(searchValue === ''){


      this._messagesService.getMessages().subscribe((response) => {

        this.messages = response;
  
      })

     }else{ 
       
       this._messagesService.filterMessage(searchValue).subscribe((response) => {

      this.messages = response;

    })
  
  }

    

    

   }

  

  ngOnInit(): void {


    this._messagesService.getMessages().subscribe((response) => {

      this.messages = response;

    })


  }

}
