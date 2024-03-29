package com.example.BlaBlaCar.entities.user;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.group.GroupDTO;
import com.example.BlaBlaCar.role.ERole;
import com.example.BlaBlaCar.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
public class UserDTO {
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    //private Set<String> roles = new HashSet<>();

    private Set<Long> groupsId = new HashSet<>();

    private Long userId;

    private float kmCoins;
}
