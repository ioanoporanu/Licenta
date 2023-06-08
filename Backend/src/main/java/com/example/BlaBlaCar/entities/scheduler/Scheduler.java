package com.example.BlaBlaCar.entities.scheduler;

import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.location.LocationService;
import com.example.BlaBlaCar.entities.ride.Ride;
import com.example.BlaBlaCar.entities.ride.RideRepository;
import com.example.BlaBlaCar.entities.ride.ride_request.RideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Scheduler {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    LocationService locationService;


    List<Ride> findRides(RideRequest rideRequest) {
        List<Ride> suitableRides = rideRepository.findAll().stream().filter(x -> x.getRideDate() == rideRequest.getRideDate())
                .filter(y -> locationService.computeDistanceBetweenLocations(rideRequest.getSource(), rideRequest.getDestination()) < 1).filter(z -> z.getAvailableSeats() > 0).collect(Collectors.toList());

        return suitableRides;
    }
}
