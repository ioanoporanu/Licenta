package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.location.Location;
import com.example.BlaBlaCar.entities.user.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RideDTO {

    private Location destination;

    private Location source;

    private Long rideDeleteId;
}
