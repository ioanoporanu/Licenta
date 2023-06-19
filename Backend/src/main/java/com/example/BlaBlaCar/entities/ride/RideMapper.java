package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.user.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring"
)
public interface RideMapper {
    Ride fromRideDTO(RideDTO rideDTO);

    @Mapping(source = "ride.id", target = "rideId")
    @Mapping(source = "ride.source", target = "source")
    @Mapping(source = "ride.destination", target = "destination")
    RideDTO fromRide(Ride ride);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(RideDTO rideDTO, @MappingTarget Ride ride);

}
