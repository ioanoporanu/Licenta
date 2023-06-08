package com.example.BlaBlaCar.entities.location;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LocationDTO {
    private float longitude;

    private float latitude;

    private String name;
}
