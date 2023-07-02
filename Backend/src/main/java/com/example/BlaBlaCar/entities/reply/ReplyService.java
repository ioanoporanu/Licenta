package com.example.BlaBlaCar.entities.reply;

import com.example.BlaBlaCar.entities.group.Group;
import com.example.BlaBlaCar.entities.group.GroupRepository;
import com.example.BlaBlaCar.entities.message.MessageRepository;
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
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MessageRepository messageRepository;

    public Long saveReply(@Valid @RequestBody ReplyDTO replyDTO) {
        Reply reply = replyMapper.fromReplyDTO(replyDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> currentUser = userRepository.findByUsername(userDetails.getUsername());

        reply.setOwnerUsername(currentUser.get().getUsername());

        reply.setMessage(messageRepository.findById(replyDTO.getMessageId()).get());

        replyRepository.save(reply);

        return reply.getId();
    }

    public List<ReplyDTO> getAll(Long messageId) {
        return messageRepository.findById(messageId).get().getReplies().stream().map(reply -> replyMapper.fromReply(reply)).collect(Collectors.toList());
    }

}
