package com.example.BlaBlaCar.entities.location;

import com.example.BlaBlaCar.entities.user.User;
import com.example.BlaBlaCar.entities.user.UserRepository;
import com.example.BlaBlaCar.entities.user.UserService;
import com.example.BlaBlaCar.exception.UnauthorizedException;
import com.example.BlaBlaCar.role.ERole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    UserRepository userRepository;

    public Long saveLocation(@Valid @RequestBody LocationDTO locationDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN) || !currentUser.get().getRoles().contains(ERole.ROLE_MODERATOR))
            throw new UnauthorizedException("You are not admin or moderator of the server");

        Location location = locationMapper.fromLocationDTO(locationDTO);

        locationRepository.save(location);

        return location.getId();
    }

    public void deleteLocation(@Valid @RequestBody LocationDTO locationDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN) || !currentUser.get().getRoles().contains(ERole.ROLE_MODERATOR))
            throw new UnauthorizedException("You are not admin or moderator of the server");

        Optional<Location> location = locationRepository.findByName(locationDTO.getName());

        locationRepository.delete(location.get());
    }

    public LocationDTO getById(Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN) || !currentUser.get().getRoles().contains(ERole.ROLE_MODERATOR))
            throw new UnauthorizedException("You are not admin or moderator of the server");

        Optional<Location> location = locationRepository.findById(id);

        LocationDTO locationDTO = locationMapper.fromLocation(location.get());

        return locationDTO;
    }

    public List<LocationDTO> getAll() {
       return locationRepository.findAll().stream().map(x -> locationMapper.fromLocation(x)).collect(Collectors.toList());
    }

    public Long updateLocation(@Valid @RequestBody LocationDTO locationDTO, Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!currentUser.get().getRoles().contains(ERole.ROLE_ADMIN) || !currentUser.get().getRoles().contains(ERole.ROLE_MODERATOR))
            throw new UnauthorizedException("You are not admin or moderator of the server");

        Optional<Location> updateLocation = locationRepository.findById(id);

        updateLocation.ifPresentOrElse((value) -> locationMapper.updateFromDto(locationDTO, updateLocation.get()), () -> new RuntimeException("You are not logged in"));
        locationRepository.save(updateLocation.get());

        return updateLocation.get().getId();
    }
}
