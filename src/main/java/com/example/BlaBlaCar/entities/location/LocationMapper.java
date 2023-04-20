package com.example.BlaBlaCar.entities.location;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface LocationMapper {
    Location fromLocationDTO(LocationDTO locationDTO);
    LocationDTO fromLocation(Location location);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(LocationDTO locationDTO, @MappingTarget Location location);
}
