package com.example.BlaBlaCar.entities.group;

import com.example.BlaBlaCar.entities.user.User;
import com.example.BlaBlaCar.entities.user.UserRepository;
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
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody GroupDTO groupDTO) {
        return groupService.saveGroup(groupDTO);
    }

    @PostMapping("/delete")
    public void delete(@Valid @RequestBody GroupDTO groupDTO) {
        groupService.deleteGroup(groupDTO);
    }

    @GetMapping("/getById/{id}")
    public GroupDTO getById(@PathVariable("id") Long id) {
        return groupService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GroupDTO> getAll() {
        return groupService.getAll();
    }

    @PutMapping("/update/{id}")
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
