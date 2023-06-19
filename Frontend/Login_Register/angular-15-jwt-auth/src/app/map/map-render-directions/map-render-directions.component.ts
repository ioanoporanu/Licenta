import {Component, Input} from '@angular/core';
import {Observable} from "rxjs";
import {MapDirectionsService} from "@angular/google-maps";
import {map} from "rxjs/operators";
import {Ride} from "../../ride/ride.interface";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-map-render-directions',
  templateUrl: './map-render-directions.component.html',
  styleUrls: ['./map-render-directions.component.css']
})
export class MapRenderDirectionsComponent {

  rideRouteShow!: Ride;

  markers = []  as  any;

  @Input()
  set rideShowRoute(ride: Ride) {
    this.rideRouteShow = ride;
    const request: google.maps.DirectionsRequest = {
      destination: this.rideRouteShow.destination.name,
      origin: this.rideRouteShow.source.name,
      travelMode: google.maps.TravelMode.DRIVING
    };

    this.directionsResults$ = this.mapDirectionsService.route(request).pipe(map(response => response.result));
  }

  options: google.maps.MapOptions = {
    zoomControl: true,
    disableDefaultUI: true,
    fullscreenControl: true,
    // maxZoom:this.maxZoom,
    // minZoom:this.minZoom,
  };

  center: google.maps.LatLngLiteral = {
    lat: 24,
    lng: 12
  };
  zoom = 4;
  directionsResults$!: Observable < google.maps.DirectionsResult | undefined > ;
  constructor(private mapDirectionsService: MapDirectionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(){
    this.route.paramMap.subscribe(params => {
      if(history.state.ride != null){
        this.rideRouteShow = history.state.ride;
        const request :google.maps.DirectionsRequest = {
          destination: this.rideRouteShow.destination.name,
          origin: this.rideRouteShow.source.name,
          travelMode: google.maps.TravelMode.DRIVING
        }
        this.directionsResults$ = this.mapDirectionsService.route(request).pipe(map(response => response.result));
        this.directionsResults$.subscribe({
            next: data => {
              console.log(this.computeTotalDistance(data as google.maps.DirectionsResult))
            }
        });
      };
    });

    navigator.geolocation.getCurrentPosition((position, ) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };

      this.markers.push({
        position: {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        },
        label: {
          color: 'blue',
          text: 'Marker label ' + (this.markers.length + 1),
        },
        title: 'Marker title ' + (this.markers.length + 1),
        info: 'Marker info ' + (this.markers.length + 1),
        options: {
          animation: google.maps.Animation.DROP,
        },
      })

      console.log(position.coords);
    }, (err) =>{
      console.warn(`ERROR(${err.code}): ${err.message}`);
    },{
      enableHighAccuracy: true,
      timeout: 5000,
      maximumAge: 0,
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
