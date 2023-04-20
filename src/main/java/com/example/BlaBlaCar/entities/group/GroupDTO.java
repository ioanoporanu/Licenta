package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class GroupDTO {

    private Set<String> usersUsername;

    private Set<String> ownersUsername;

    private String name;

    private Long groupDeleteId;

    private String userDeleteUsername;

    private String ownerDeleteUsername;

}
