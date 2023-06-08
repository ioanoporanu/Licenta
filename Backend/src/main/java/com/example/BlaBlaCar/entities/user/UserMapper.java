package com.example.BlaBlaCar.entities.user;

import com.example.BlaBlaCar.entities.group.GroupRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring"
)
public abstract class UserMapper {
    @Autowired
    GroupRepository groupRepository;

    abstract User fromUserDTO(UserDTO userDTO);

    abstract UserDTO fromUser(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UserDTO userDTO, @MappingTarget User user){
        if(userDTO.getUsername() != null)
            user.setUsername(userDTO.getUsername());

        if(userDTO.getEmail() != null)
            user.setEmail(userDTO.getEmail());

        if(userDTO.getPassword() != null)
            user.setPassword(userDTO.getPassword());

        if(userDTO.getGroupsId() != null)
            user.setGroups(new HashSet<>(groupRepository.findAllById(userDTO.getGroupsId())));
    }
}
