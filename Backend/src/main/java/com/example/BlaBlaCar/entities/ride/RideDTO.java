package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.location.LocationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
public class RideDTO {
    private LocationDTO destination;

    private LocationDTO source;

    private Long rideId;

    private Date rideDate;

    private int availableSeats;

    private Long customerAddId;

    private float rideLength;

    private Long sharedGroupAddId;

    private Long customerDeleteId;

    private Set<Long> customersId = new HashSet<>();
}
