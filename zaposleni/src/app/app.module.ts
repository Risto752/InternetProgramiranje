import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { MessagesComponent } from './messages/messages.component';
import { ScheduleModule, RecurrenceEditorModule, DayService, WeekService, MonthService, MonthAgendaService } from '@syncfusion/ej2-angular-schedule';
import { FlightsScheduleComponent } from './flights-schedule/flights-schedule.component';
import { FlightlineComponent } from './flightline/flightline.component';
import { FlightComponent } from './flight/flight.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    MenuComponent,
    MessagesComponent,
    FlightsScheduleComponent,
    FlightlineComponent,
    FlightComponent

  ],
  imports: [
    BrowserModule,
    ScheduleModule,
    RecurrenceEditorModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: '' , component : LoginFormComponent},
      {path: 'menu' , component : MenuComponent},
      {path: "messages", component : MessagesComponent},
      {path : 'schedule', component : FlightsScheduleComponent},
      {path : 'flightline', component : FlightlineComponent},
      {path : 'flight', component : FlightComponent}
    ])
  ],
  providers: [ DayService, WeekService, MonthService, MonthAgendaService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
