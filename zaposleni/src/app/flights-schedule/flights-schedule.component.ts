import { Component, OnInit} from '@angular/core';
import { DataManager, WebApiAdaptor } from '@syncfusion/ej2-data';
import { EventSettingsModel, View } from  '@syncfusion/ej2-angular-schedule';

@Component({
  selector: 'app-flights-schedule',
  templateUrl: './flights-schedule.component.html',
  styleUrls: ['./flights-schedule.component.css']
})
export class FlightsScheduleComponent implements OnInit {

    public setView : View = "Month";

  private dataManager: DataManager = new DataManager({
    url: 'http://localhost:8081/flights',
    adaptor: new WebApiAdaptor,
    crossDomain: true
 });

 public eventObject: EventSettingsModel = {
   
  dataSource: this.dataManager


};




  constructor() { }

  ngOnInit(): void {
  }

}
