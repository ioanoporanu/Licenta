package com.example.BlaBlaCar.entities.ride;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("ride")
public class RideController {
    @Autowired
    RideService rideService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody RideDTO rideDTO) {
        return rideService.saveRide(rideDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@Valid @RequestBody RideDTO rideDTO) {
        rideService.deleteRide(rideDTO);
    }

    @GetMapping("/getById/{id}")
    public RideDTO getById(@PathVariable("id") Long id) {
        return rideService.getById(id);
    }

    @GetMapping("/getAll")
    public List<RideDTO> getAll() {
        return rideService.getAll();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody RideDTO rideDTO) {
        return rideService.updateRide(rideDTO, id);
    }
}
