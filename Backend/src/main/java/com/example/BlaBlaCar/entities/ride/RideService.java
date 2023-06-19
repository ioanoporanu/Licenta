package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.group.GroupRepository;
import com.example.BlaBlaCar.entities.location.LocationService;
import com.example.BlaBlaCar.entities.ride.ride_request.RideRequest;
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

    @Autowired
    LocationService locationService;

    public Long saveRide(@Valid @RequestBody RideDTO rideDTO) {
        Ride ride = rideMapper.fromRideDTO(rideDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        userRepository.findById(currentUser.get().getId()).get().getOwnedRides().add(ride);

        currentUser.ifPresentOrElse(ride::setOwner, () -> new RuntimeException("You are not logged in"));

        rideRepository.save(ride);

        return ride.getId();
    }

    public void deleteRide(Long id) {
        Optional<Ride> ride = rideRepository.findById(id);

        rideRepository.delete(ride.get());
    }

    public void completeRide(Long id) {
        

        Optional<Ride> ride = rideRepository.findById(id);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        currentUser.get().setKmCoins(currentUser.get().getKmCoins() + ride.get().getRideLength() * ride.get().getCustomers().size());

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

    public List<RideDTO> getOwnedRides() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());
        return currentUser.get().getOwnedRides().stream().map(x -> rideMapper.fromRide(x)).collect(Collectors.toList());
    }

    public Long updateRide(@Valid @RequestBody RideDTO rideDTO, Long id) {
        Optional<Ride> updateRide = rideRepository.findById(id);
        updateRide.ifPresentOrElse((value) -> rideMapper.updateFromDto(rideDTO, updateRide.get()), () -> new RuntimeException("You are not logged in"));

        if(rideDTO.getCustomerAddId() != null) {
            Optional<User> currentUser = userRepository.findById(rideDTO.getCustomerAddId());
            updateRide.ifPresentOrElse((value) -> updateRide.get().getCustomers().add(currentUser.get()), () -> new RuntimeException("You are not logged in"));
            currentUser.get().getRides().add(updateRide.get());
            currentUser.get().setKmCoins( currentUser.get().getKmCoins() - rideDTO.getRideLength());

        }

        if(rideDTO.getCustomerDeleteId() != null) {
            Optional<User> currentUser = userRepository.findById(rideDTO.getCustomerDeleteId());
            updateRide.ifPresentOrElse((value) -> updateRide.get().getCustomers().remove(currentUser.get()), () -> new RuntimeException("You are not logged in"));
            currentUser.get().getRides().remove(updateRide.get());
            currentUser.get().setKmCoins(currentUser.get().getKmCoins() + rideDTO.getRideLength());
        }
        rideRepository.save(updateRide.get());

        return updateRide.get().getId();
    }

    List<RideDTO> findRides(RideRequest rideRequest) {
        List<Ride> suitableRides = rideRepository.findAll().stream()
                .filter(x -> x.getRideDate().compareTo(rideRequest.getRideDate()) == 0)
                .filter(y -> locationService.computeDistanceBetweenLocations(rideRequest.getSource(), y.getSource()) < 1)
                .filter(p -> locationService.computeDistanceBetweenLocations(rideRequest.getDestination(), p.getDestination()) < 1)
                .filter(z -> z.getAvailableSeats() > 0)
                .collect(Collectors.toList());

        List <RideDTO> suitableRidesDTO = suitableRides.stream().map(ride -> rideMapper.fromRide(ride)).collect(Collectors.toList());

        suitableRidesDTO.forEach(x -> x.setCustomersId(rideRepository.findById(x.getRideId()).get().getCustomers().stream().map(customer -> customer.getId()).collect(Collectors.toSet())));

        return suitableRidesDTO;
    }
}
