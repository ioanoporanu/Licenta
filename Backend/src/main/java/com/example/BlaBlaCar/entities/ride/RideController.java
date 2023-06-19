package com.example.BlaBlaCar.entities.ride;

import com.example.BlaBlaCar.entities.ride.ride_request.RideRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/ride")
public class RideController {
    @Autowired
    RideService rideService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody RideDTO rideDTO) {
        return rideService.saveRide(rideDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        rideService.deleteRide(id);
    }

    @DeleteMapping("/complete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void complete(@PathVariable("id") Long id) {
        rideService.completeRide(id);
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public RideDTO getById(@PathVariable("id") Long id) {
        return rideService.getById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<RideDTO> getAll() {
        return rideService.getAll();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody RideDTO rideDTO) {
        return rideService.updateRide(rideDTO, id);
    }

    @PostMapping("/rideRequest")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<RideDTO> getRides(@Valid @RequestBody RideRequest rideRequest){
        return rideService.findRides(rideRequest);
    }

    @GetMapping("/getOwnedRides")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<RideDTO> getOwnedRides() {
        return rideService.getOwnedRides();
    }
}
