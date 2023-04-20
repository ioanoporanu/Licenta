package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.user.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring"
)
public abstract class GroupMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    Group fromGroupDTO(GroupDTO groupDTO) {
        Group group = new Group();

        group.setName(groupDTO.getName());

        group.setOwners(new HashSet<>(groupDTO.getOwnersUsername().stream().map(x -> userRepository.findByUsername(x).get()).collect(Collectors.toList())));

        group.setUsers(new HashSet<>(groupDTO.getUsersUsername().stream().map(x -> userRepository.findByUsername(x).get()).collect(Collectors.toList())));

        return group;
    }


    GroupDTO fromGroup(Group group){
        GroupDTO groupDTO = new GroupDTO();

        groupDTO.setName(group.getName());

        groupDTO.setOwnersUsername(new HashSet<>(group.getOwners().stream().map(x -> x.getUsername()).collect(Collectors.toList())));

        groupDTO.setUsersUsername(new HashSet<>(group.getUsers().stream().map(x -> x.getUsername()).collect(Collectors.toList())));

        groupDTO.setGroupDeleteId(group.getId());

        return groupDTO;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(GroupDTO groupDTO, Group group){
        if(groupDTO.getName() != null)
            group.setName(groupDTO.getName());

        if(groupDTO.getOwnerDeleteUsername() != null)
            group.getOwners().remove(userRepository.findByUsername(groupDTO.getOwnerDeleteUsername()).get());

        if(groupDTO.getUserDeleteUsername() != null){
            group.getUsers().remove(userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get());

            UserDTO userDTO = new UserDTO();

            userDTO.setGroupsId(new HashSet<>(userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get().getGroups().stream().map(x -> x.getId()).collect(Collectors.toList())));

            userDTO.getGroupsId().remove(group.getId());

            userService.updateUser(userDTO, userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get().getId());
        }

    }
}
