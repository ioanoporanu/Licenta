package com.example.BlaBlaCar.entities.message;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.group.GroupDTO;
import com.example.BlaBlaCar.entities.group.GroupRepository;
import com.example.BlaBlaCar.entities.user.User;
import com.example.BlaBlaCar.entities.user.UserRepository;
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
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    public Long saveMessage(@Valid @RequestBody MessageDTO messageDTO) {
        Message message = messageMapper.fromMessageDTO(messageDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        currentUser.ifPresentOrElse(message::setOwner, () -> new RuntimeException("You are not logged in"));

        Optional<Group> currentGroup = groupRepository.findById(messageDTO.getGroupId());

        currentGroup.ifPresentOrElse(message::setGroup, () -> new RuntimeException("You are not logged in"));

        message.setOwnerName(message.getOwner().getUsername());

        messageRepository.save(message);

        return message.getId();
    }

    public void deleteMessage(@Valid @RequestBody MessageDTO messageDTO) {
        Optional<Message> message = messageRepository.findById(messageDTO.getMessageDeleteId());

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!message.get().getOwner().equals(currentUser))
            throw new UnauthorizedException("You are not the owner of this message");

        messageRepository.delete(message.get());
    }

    public MessageDTO getById(Long id) {
        Optional<Message> message = messageRepository.findById(id);

        MessageDTO messageDTO = messageMapper.fromMessage(message.get());

        return messageDTO;
    }

    public List<MessageDTO> getAll(Long groupId) {
        Optional<Group> group = this.groupRepository.findById(groupId);
        return group.get().getMessages().stream().map(x -> messageMapper.fromMessage(x)).collect(Collectors.toList());
    }

    public Long updateMessage(@Valid @RequestBody MessageDTO messageDTO, Long id) {
        Optional<Message> updateMessage = messageRepository.findById(id);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        if(!updateMessage.get().getOwner().equals(currentUser))
            throw new UnauthorizedException("You are not the owner of this message");

        updateMessage.ifPresentOrElse((value) -> messageMapper.updateFromDto(messageDTO, updateMessage.get()), () -> new RuntimeException("You are not logged in"));
        messageRepository.save(updateMessage.get());

        return updateMessage.get().getId();
    }
}
