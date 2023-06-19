package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.ride.RideDTO;
import com.example.BlaBlaCar.entities.ride.RideMapper;
import com.example.BlaBlaCar.entities.ride.RideRepository;
import com.example.BlaBlaCar.entities.ride.RideService;
import com.example.BlaBlaCar.entities.user.User;
import com.example.BlaBlaCar.entities.user.UserRepository;
import com.example.BlaBlaCar.entities.user.UserService;
import com.example.BlaBlaCar.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RideMapper rideMapper;

    @Autowired
    RideRepository rideRepository;

    public Long saveGroup(@Valid @RequestBody GroupDTO groupDTO) {
        Group group = groupMapper.fromGroupDTO(groupDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        group.getUsers().forEach(user -> user.getGroups().add(group));

        currentUser.get().getGroups().add(group);

        currentUser.ifPresentOrElse(group.getOwners()::add, () -> new RuntimeException("You are not logged in"));

        currentUser.ifPresentOrElse(group.getUsers()::add, () -> new RuntimeException("You are not logged in"));

        groupRepository.save(group);

        return group.getId();
    }

    public void deleteGroup(@Valid @RequestBody Long id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        Optional<Group> group = groupRepository.findById(id);

        if(!group.get().getOwners().contains(currentUser.get()))
            throw new UnauthorizedException("You are not admin of this group");

        group.get().getUsers().forEach((value) -> groupMapper.updateFromDto(GroupDTO.builder().groupDeleteId(group.get().getId()).build(), group.get()));

        groupRepository.delete(group.get());
    }

    public GroupDTO getById(Long id) {
        Optional<Group> group = groupRepository.findById(id);

        GroupDTO groupDTO = groupMapper.fromGroup(group.get());

        return groupDTO;
    }

    public List<GroupDTO> getAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());
        return currentUser.get().getGroups().stream().map(x -> groupMapper.fromGroup(x)).collect(Collectors.toList());
    }

    public Long updateGroup(@Valid @RequestBody GroupDTO groupDTO, Long id) {
        Optional<Group> updateGroup = groupRepository.findById(id);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!updateGroup.get().getOwners().contains(currentUser.get()))
            throw new UnauthorizedException("You are not admin of this group");

        updateGroup.ifPresentOrElse((value) -> groupMapper.updateFromDto(groupDTO, updateGroup.get()), () -> new RuntimeException("You are not logged in"));
        groupRepository.save(updateGroup.get());

        return updateGroup.get().getId();
    }

    public List<RideDTO> getSharedRides(Long id){
        Optional<Group> group = groupRepository.findById(id);

        List<RideDTO> sharedRides = group.get().getSharedRides().stream().map(x -> rideMapper.fromRide(x)).collect(Collectors.toList());
        sharedRides.forEach(x -> x.setCustomersId(rideRepository.findById(x.getRideId()).get().getCustomers().stream().map(customer -> customer.getId()).collect(Collectors.toSet())));
        return sharedRides;
    }

    public Long shareRide(GroupDTO groupDTO){
        Optional<Group> group = groupRepository.findById(groupDTO.getGroupDeleteId());

        group.get().getSharedRides().add(rideRepository.findById(groupDTO.getRideShareId()).get());

        rideRepository.findById(groupDTO.getRideShareId()).get().getSharedGroups().add(group.get());

        groupRepository.save(group.get());

        return groupDTO.getGroupDeleteId();
    }
}
