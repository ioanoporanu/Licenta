package com.example.BlaBlaCar.entities.location;

import com.example.BlaBlaCar.entities.group.GroupDTO;
import com.example.BlaBlaCar.entities.group.GroupService;
import com.example.BlaBlaCar.exception.ErrorMessage;
import com.example.BlaBlaCar.exception.NotFoundException;
import com.example.BlaBlaCar.exception.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody LocationDTO locationDTO) {
        return locationService.saveLocation(locationDTO);
    }

    @PostMapping("/delete")
    public void delete(@Valid @RequestBody LocationDTO locationDTO) {
        locationService.deleteLocation(locationDTO);
    }

    @GetMapping("/getById/{id}")
    public LocationDTO getById(@PathVariable("id") Long id) {
        return locationService.getById(id);
    }

    @GetMapping("/getAll")
    public List<LocationDTO> getAll() {
        return locationService.getAll();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody LocationDTO locationDTO) {
        return locationService.updateLocation(locationDTO, id);
    }

    @ExceptionHandler({ NotFoundException.class})
    public ResponseEntity<ErrorMessage> NotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UnauthorizedException.class})
    public ResponseEntity<ErrorMessage> UnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
