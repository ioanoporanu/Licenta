package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.exceptions.ErrorMessage;
import com.example.BlaBlaCar.exceptions.NotFoundException;
import com.example.BlaBlaCar.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Long save(@Valid @RequestBody GroupDTO groupDTO) {
        return groupService.saveGroup(groupDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping("/getById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public GroupDTO getById(@PathVariable("id") Long id) {
        return groupService.getById(id);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<GroupDTO> getAll() {
        return groupService.getAll();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(groupDTO, id);
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
