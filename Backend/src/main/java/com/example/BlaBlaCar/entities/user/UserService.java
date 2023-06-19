package com.example.BlaBlaCar.entities.user;

import com.example.BlaBlaCar.entities.group.GroupDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public Long updateUser(@Valid @RequestBody UserDTO userDTO, Long id) {
        Optional<User> updateUser = userRepository.findById(id);

        updateUser.ifPresentOrElse((value) -> userMapper.updateFromDto(userDTO, updateUser.get()), () -> new RuntimeException("You are not logged in"));
        userRepository.save(updateUser.get());

        return updateUser.get().getId();
    }

    public UserDTO getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        UserDTO userDTO = userMapper.fromUser(currentUser.get());

        userDTO.setUserId(currentUser.get().getId());

        //userDTO.setRoles(currentUser.get().getRoles().stream().map(x -> x.getName().toString()).collect(Collectors.toSet()));

        return userDTO;
    }
}
