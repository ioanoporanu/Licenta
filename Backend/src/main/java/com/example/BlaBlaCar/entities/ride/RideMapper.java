package com.example.BlaBlaCar.entities.ride;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface RideMapper {
    Ride fromRideDTO(RideDTO rideDTO);

    RideDTO fromRide(Ride ride);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(RideDTO RideDTO, @MappingTarget Ride Ride);
}
