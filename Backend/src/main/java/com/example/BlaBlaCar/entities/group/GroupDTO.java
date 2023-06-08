package com.example.BlaBlaCar.entities.group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Builder
@Setter
@Getter
public class GroupDTO {

    private Set<String> usersUsername;

    private Set<String> ownersUsername;

    private String name;

    private String description;

    private Long groupDeleteId;

    private String userDeleteUsername;

    private String ownerDeleteUsername;

    private String userAddUsername;

    private String ownerAddUsername;

}
