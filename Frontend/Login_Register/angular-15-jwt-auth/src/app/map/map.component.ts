import {Component, ElementRef, NgZone, ViewChild, ViewChildren} from '@angular/core';
import { GoogleMap } from '@angular/google-maps';
import {GroupDisplayComponent} from "../group/group-display/group-display.component";
import {Ride} from "../ride/ride.interface";
import {RidesDisplayComponent} from "../ride/rides-display/rides-display.component";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent {
  title = 'angular-google-map-search';

  @ViewChildren(GoogleMap)
  public map!: GoogleMap;

  @ViewChildren(RidesDisplayComponent)
  public rides!: RidesDisplayComponent;

  rideShowRoute!: Ride;

  zoom = 12;
  center!: google.maps.LatLngLiteral;
  options: google.maps.MapOptions = {
    zoomControl: true,
    disableDefaultUI: true,
    fullscreenControl: true,
    // maxZoom:this.maxZoom,
    // minZoom:this.minZoom,
  };
  latitude!: any;
  longitude!: any;

  constructor(private ngZone: NgZone) {}

  ngOnInit() {
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };

    });
  }

  receiveRoute(ride: Ride){
    console.log("plm");
    this.rideShowRoute = ride;
  }
}
