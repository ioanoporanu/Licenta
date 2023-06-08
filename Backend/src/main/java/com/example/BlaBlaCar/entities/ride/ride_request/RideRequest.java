package com.example.BlaBlaCar.entities.ride.ride_request;

import com.example.BlaBlaCar.entities.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RideRequest {
    private Location destination;

    private Location source;

    private LocalDateTime rideDate;

    private float priority;
}
