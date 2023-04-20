package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.group.GroupRepository;
import com.example.BlaBlaCar.entities.ride.Ride;
import com.example.BlaBlaCar.entities.ride.RideDTO;
import com.example.BlaBlaCar.entities.user.User;
import com.example.BlaBlaCar.entities.user.UserRepository;
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
public class RideService {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    RideMapper rideMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    public Long saveRide(@Valid @RequestBody RideDTO rideDTO) {
        Ride ride = rideMapper.fromRideDTO(rideDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        currentUser.ifPresentOrElse(ride::setOwner, () -> new RuntimeException("You are not logged in"));

        rideRepository.save(ride);

        return ride.getId();
    }

    public void deleteride(@Valid @RequestBody RideDTO rideDTO) {
        Optional<Ride> ride = rideRepository.findById(rideDTO.getRideDeleteId());


        rideRepository.delete(ride.get());
    }

    public RideDTO getById(Long id) {
        Optional<Ride> ride = rideRepository.findById(id);

        RideDTO rideDTO = rideMapper.fromRide(ride.get());

        return rideDTO;
    }

    public List<RideDTO> getAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());
        return currentUser.get().getRides().stream().map(x -> rideMapper.fromRide(x)).collect(Collectors.toList());
    }

    public Long updateRide(@Valid @RequestBody RideDTO rideDTO, Long id) {
        Optional<Ride> updateRide = rideRepository.findById(id);

        updateRide.ifPresentOrElse((value) -> rideMapper.updateFromDto(rideDTO, updateRide.get()), () -> new RuntimeException("You are not logged in"));
        rideRepository.save(updateRide.get());

        return updateRide.get().getId();
    }
}
