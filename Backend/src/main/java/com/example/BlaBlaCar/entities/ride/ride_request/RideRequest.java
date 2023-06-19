package com.example.BlaBlaCar.entities.ride.ride_request;

import com.example.BlaBlaCar.entities.location.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
public class RideRequest {
    private Location destination;

    private Location source;

    private Date rideDate;
}
