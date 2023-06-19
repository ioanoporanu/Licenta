import { Component } from '@angular/core';

@Component({
  selector: 'app-date-time-pick',
  templateUrl: './date-time-pick.component.html',
  styleUrls: ['./date-time-pick.component.css']
})
export class DateTimePickComponent {
  rideDate: Date = new Date();

}
