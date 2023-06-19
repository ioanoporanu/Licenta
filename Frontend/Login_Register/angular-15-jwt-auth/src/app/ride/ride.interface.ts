export interface Ride {
  destination: Location;

  source: Location;

  rideDate: Date;

  availableSeats: number;

  customersId: number[];

  rideId: number;

  customerAddId: number;

  customerDeleteId: number;

  rideLength: number;
}

export interface Location {
  longitude: number;
  latitude: number;
  name: string;
}




