import {Component, ElementRef, NgZone, ViewChild} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {GoogleMap} from "@angular/google-maps";
import {RideService} from "../../_services/ride.service";
import {DatePipe} from "@angular/common";
import {User} from "../../user/user.interface";
import {Ride} from "../ride.interface";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-search-ride',
  templateUrl: './search-ride.component.html',
  styleUrls: ['./search-ride.component.css']
})
export class SearchRideComponent {
  content!: string;

  availableRides!: Set<Ride>;

  currentUser!: User;

  @ViewChild('search')
  public searchElementRefSource!: ElementRef;

  @ViewChild('search1')
  public searchElementRefDestination!: ElementRef;
  @ViewChild(GoogleMap)
  public map!: GoogleMap;

  options: google.maps.MapOptions = {
  };

  screenVisible: Map<string, boolean> = new Map<string, boolean>().set("Drive", true).set("Travel", false);
  screenSelected: Map<string, boolean> = new Map<string, boolean>().set("Drive", true).set("Travel", false);

  availableRidesDisplay: boolean = false;

  searchSourceText: string = "Search pick up point";
  searchDestinationText: string = "Search destination point";

  autocompleteSource!: google.maps.places.Autocomplete;
  autocompleteDestination!: google.maps.places.Autocomplete;

  form: FormGroup = new FormGroup ({
    pickUpPoint: new FormControl(null),
    destinationPoint: new FormControl(null),
    pickUpDate: new FormControl (null)
  });

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private userService: UserService,
              private rideService: RideService,
              public datePipe: DatePipe) { }

  ngAfterViewInit(): void {
    this.autocompleteSource = new google.maps.places.Autocomplete(
      this.searchElementRefSource.nativeElement
    );

    this.autocompleteDestination = new google.maps.places.Autocomplete(
      this.searchElementRefDestination.nativeElement
    )
  }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe({
      next: data => {
        this.currentUser = JSON.parse(data) as User;

      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }

  onClickDrive(){
    this.screenVisible.set("Drive", true);
    this.screenVisible.set("Travel", false);

    this.screenSelected.set("Drive", true);
    this.screenSelected.set("Travel", false);
  }

  onClickTravel(){
    this.screenVisible.set("Drive", false);
    this.screenVisible.set("Travel", true);

    this.screenSelected.set("Drive", false);
    this.screenSelected.set("Travel", true);
  }

  onClickOrder () {
    this.rideService.findRides(this.form.controls['pickUpDate'].value, this.autocompleteSource.getPlace(), this.autocompleteDestination.getPlace()).subscribe({
      next: data => {
        this.availableRides = data as Set<Ride>;
        for(let a of this.availableRides){
          a.customersId = Object.values(a.customersId);
        }
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
    this.availableRidesDisplay = true;

  }

  onClickBook (ride: Ride) {
    if(!confirm("Are you sure to book this ride"))
      return;

    this.userService.getCurrentUser().subscribe({
      next: data => {
        let user = JSON.parse(data) as User;

        if(user.kmCoins < ride.rideLength){
          alert("You don't have enough kmCoins")
          return;
        }

        ride.customerAddId = user.userId;
        ride.availableSeats--;
        this.rideService.updateRide(ride).subscribe({
          next: data => {
            window.location.reload();
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
}
