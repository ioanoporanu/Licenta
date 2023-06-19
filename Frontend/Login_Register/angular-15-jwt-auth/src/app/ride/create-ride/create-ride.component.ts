import {Component, ElementRef, ViewChild} from '@angular/core';
import {GoogleMap, MapDirectionsService} from "@angular/google-maps";
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../_services/user.service";
import {RideService} from "../../_services/ride.service";
import {map} from "rxjs/operators";
import {MapRenderDirectionsComponent} from "../../map/map-render-directions/map-render-directions.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-create-ride',
  templateUrl: './create-ride.component.html',
  styleUrls: ['./create-ride.component.css']
})
export class CreateRideComponent {
  content!: string;

  @ViewChild('search')
  public searchElementRefSource!: ElementRef;

  @ViewChild('search1')
  public searchElementRefDestination!: ElementRef;
  @ViewChild(GoogleMap)
  public map!: GoogleMap;

  options: google.maps.MapOptions = {
  };

  searchSourceText: string = "Search pick up point";
  searchDestinationText: string = "Search destination point";

  autocompleteSource!: google.maps.places.Autocomplete;
  autocompleteDestination!: google.maps.places.Autocomplete;

  form: FormGroup = new FormGroup ({
    pickUpPoint: new FormControl(null),
    destinationPoint: new FormControl(null),
    pickUpDate: new FormControl (null),
    availableSeats: new FormControl(null)
  });

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private userService: UserService,
              private rideService: RideService,
              private mapDirectionsService: MapDirectionsService,
              private route: ActivatedRoute) { }

  ngAfterViewInit(): void {
    this.autocompleteSource = new google.maps.places.Autocomplete(
      this.searchElementRefSource.nativeElement
    );

    this.autocompleteDestination = new google.maps.places.Autocomplete(
      this.searchElementRefDestination.nativeElement
    )
  }

  ngOnInit(): void {
  }

  onClickSubmitNow () {

      const request: google.maps.DirectionsRequest = {
        destination: this.autocompleteDestination.getPlace().name!,
        origin: this.autocompleteSource.getPlace().name!,
        travelMode: google.maps.TravelMode.DRIVING
      };

      let directionsResults$ = this.mapDirectionsService.route(request).pipe(map(response => response.result));
      directionsResults$.subscribe({
        next: data => {
          this.rideService.createRide(this.form.controls['pickUpDate'].value, this.autocompleteSource.getPlace(), this.autocompleteDestination.getPlace(), this.form.controls['availableSeats'].value, this.computeTotalDistance(data as google.maps.DirectionsResult)).subscribe({
            next: data => {
              window.alert("Your ride submission was successful");
              window.location.reload();
            },
            error: err => {
              this.errorMessage = err.error.message;
              this.isSignUpFailed = true;
            }
          });
        }
      });
    }

  computeTotalDistance(result: google.maps.DirectionsResult) {
    let total = 0;
    const myroute = result.routes[0];

    if (!myroute) {
      return;
    }

    for (let i = 0; i < myroute.legs.length; i++) {
      total += myroute.legs[i]!.distance!.value;
    }

    total = total / 1000;
    return total;
  }
}
