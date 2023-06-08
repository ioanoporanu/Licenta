package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.location.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class RideDTO {

    private Location destination;

    private Location source;

    private Long rideDeleteId;

    private LocalDateTime rideDate;

    private int availableSeats;
}
