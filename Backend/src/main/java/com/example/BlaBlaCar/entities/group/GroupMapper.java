package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.user.*;
import com.example.BlaBlaCar.exceptions.NotFoundException;
import com.example.BlaBlaCar.exceptions.UserAlreadyExistException;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
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
        GroupDTO groupDTO = GroupDTO.builder().name(group.getName())
                        .ownersUsername(new HashSet<>(group.getOwners().stream().map(x -> x.getUsername()).collect(Collectors.toList())))
                                .usersUsername(new HashSet<>(group.getUsers().stream().map(x -> x.getUsername()).collect(Collectors.toList())))
                                        .groupDeleteId(group.getId()).build();

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

            group.getOwners().remove(userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get());

            UserDTO userDTO = UserDTO.builder().groupsId(new HashSet<>(userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get().getGroups().stream().map(x -> x.getId()).collect(Collectors.toList())))
                            .build();

            userDTO.getGroupsId().remove(group.getId());

            userService.updateUser(userDTO, userRepository.findByUsername(groupDTO.getUserDeleteUsername()).get().getId());
        }

        if(groupDTO.getOwnerAddUsername() != null){
            if(group.getUsers().contains(userRepository.findByUsername(groupDTO.getOwnerAddUsername())))
                throw new NotFoundException("User is not in this group");

            group.getOwners().add(userRepository.findByUsername(groupDTO.getOwnerAddUsername()).get());
        }

        if(groupDTO.getUserAddUsername() != null){
            if(group.getUsers().contains(userRepository.findByUsername(groupDTO.getUserAddUsername())))
                throw new UserAlreadyExistException("User is already in this group");

            group.getUsers().add(userRepository.findByUsername(groupDTO.getUserAddUsername()).get());

            userRepository.findByUsername(groupDTO.getUserAddUsername()).get().getGroups().add(group);
        }

    }
}
