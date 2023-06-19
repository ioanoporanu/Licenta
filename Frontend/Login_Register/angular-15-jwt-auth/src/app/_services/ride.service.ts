import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Ride} from "../ride/ride.interface";

const API_URL = 'http://localhost:9091/ride/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class RideService {
  constructor(private http: HttpClient) {}

  createRide(rideDate: Date, pickUpPoint: google.maps.places.PlaceResult, destinationPoint: google.maps.places.PlaceResult, availableSeats: number, rideLength: number | undefined): Observable<any> {
    console.log(rideDate);
    return this.http.post(
      API_URL + 'create',
      {
        rideDate,
        "source" : {
          "longitude" : pickUpPoint.geometry?.location?.lng(),
          "latitude" : pickUpPoint.geometry?.location?.lat(),
          "name" : pickUpPoint.name
        },
        "destination" : {
          "longitude" : destinationPoint.geometry?.location?.lng(),
          "latitude" : destinationPoint.geometry?.location?.lat(),
          "name" : destinationPoint.name
        },
        availableSeats,
        rideLength,
      },
      httpOptions
    );
  }

  findRides(rideDate: Date, pickUpPoint: google.maps.places.PlaceResult, destinationPoint: google.maps.places.PlaceResult): Observable<any> {
    console.log(rideDate);
    return this.http.post(
      API_URL + 'rideRequest',
      {
        rideDate,
        "source" : {
          "longitude" : pickUpPoint.geometry?.location?.lng(),
          "latitude" : pickUpPoint.geometry?.location?.lat(),
          "name" : pickUpPoint.name
        },
        "destination" : {
          "longitude" : destinationPoint.geometry?.location?.lng(),
          "latitude" : destinationPoint.geometry?.location?.lat(),
          "name" : destinationPoint.name
        }
      },
      httpOptions
    );
  }

  updateRide(ride: Ride) : Observable<any> {
    console.log(API_URL + 'update/' + ride.rideId);
    console.log(ride);
    console.log(JSON.stringify(ride));
    return this.http.put(
      API_URL + 'update/' + ride.rideId,
      JSON.stringify(ride),
      httpOptions
    );
  }

  getRides() : Observable<any> {
    return this.http.get(
      API_URL + 'getAll',
      httpOptions
    );
  }

  getOwnedRides() : Observable<any> {
    return this.http.get(
      API_URL + 'getOwnedRides',
      httpOptions
    );
  }

  deleteRide(id: number): Observable<any> {
    return this.http.delete(
      API_URL + "delete/" + id,
      httpOptions
    )
  }

  completeRide(id: number): Observable<any> {
    return this.http.delete(
      API_URL + "complete/" + id,
      httpOptions
    )
  }
}
