import {Component, ElementRef, EventEmitter, Output, ViewChild} from '@angular/core';
import {Ride} from "../ride.interface";
import {User} from "../../user/user.interface";
import {UserService} from "../../_services/user.service";
import {RideService} from "../../_services/ride.service";
import {DatePipe} from "@angular/common";
import {GroupDisplayComponent} from "../../group/group-display/group-display.component";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {FeedModalComponent} from "../../group/feed-element/feed-modal/feed-modal.component";
import {GroupDisplayModalComponent} from "../../group/group-display-modal/group-display-modal.component";

@Component({
  selector: 'app-rides-display',
  templateUrl: './rides-display.component.html',
  styleUrls: ['./rides-display.component.css']
})
export class RidesDisplayComponent {

  bookedRides!: Set<Ride>;
  ownedRides!: Set<Ride>;

  @Output() showRoute: EventEmitter<any> = new EventEmitter();

  screenVisible: Map<string, boolean> = new Map<string, boolean>().set("Booked", true).set("Owned", false);
  screenSelected: Map<string, boolean> = new Map<string, boolean>().set("Booked", true).set("Owned", false);

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private userService: UserService,
              private rideService: RideService,
              public datePipe: DatePipe,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.rideService.getRides().subscribe({
      next: data => {
        this.bookedRides = data as Set<Ride>;
        console.log(data);
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });

    this.rideService.getOwnedRides().subscribe({
      next: data => {
        console.log(data);
        this.ownedRides = data as Set<Ride>;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }

  onClickBooked(){
    this.screenVisible.set("Booked", true);
    this.screenVisible.set("Owned", false);

    this.screenSelected.set("Booked", true);
    this.screenSelected.set("Owned", false);
  }

  onClickOwned(){
    this.screenVisible.set("Booked", false);
    this.screenVisible.set("Owned", true);

    this.screenSelected.set("Booked", false);
    this.screenSelected.set("Owned", true);
  }

  onClickUnBook(ride: Ride){
    if(!confirm("Are you sure to cancel booking this ride"))
      return;
    this.userService.getCurrentUser().subscribe({
      next: data => {
        let user = JSON.parse(data) as User;
        ride.customerDeleteId = user.userId;
        ride.availableSeats++;

        this.rideService.updateRide(ride).subscribe({
          next: data => {
            window.location.reload()
          },
          error: err => {
            this.errorMessage = err.error.message;
            this.isSignUpFailed = true;
          }
        });

        ride.customerDeleteId = 0;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });

  }

  onClickForfeit(ride: Ride){
    if(!confirm("Are you sure to delete ride"))
      return;

    this.rideService.deleteRide(ride.rideId).subscribe({
      next: data => {
        window.location.reload();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });

  }

  onClickDone(ride: Ride){
    if(!confirm("Are you sure the ride was completed?"))
      return;

    this.rideService.completeRide(ride.rideId).subscribe({
      next: data => {
        window.location.reload();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });

  }

  setRideRouteShow (ride: Ride) {
    this.showRoute.emit(ride);
  }

  sharedRide(ride: Ride){
    console.log("cacat");
    const dialogRef = this.dialog.open(GroupDisplayModalComponent,{
      width: '700px',
      height:'700px',
      data: ride.rideId
    });
  }
}
