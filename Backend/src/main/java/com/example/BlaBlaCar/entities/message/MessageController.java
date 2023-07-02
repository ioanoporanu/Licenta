package com.example.BlaBlaCar.entities.message;

import com.example.BlaBlaCar.entities.reply.Reply;
import com.example.BlaBlaCar.entities.reply.ReplyDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody MessageDTO messageDTO) {
        return messageService.saveMessage(messageDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
    }

    @GetMapping("/getById/{id}")
    public MessageDTO getById(@PathVariable("id") Long id) {
        return messageService.getById(id);
    }

    @GetMapping("/getAll/{id}")
    public List<MessageDTO> getAll(@PathVariable("id") Long id) {
        return messageService.getAll(id);
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody MessageDTO messageDTO) {
        return messageService.updateMessage(messageDTO, id);
    }

//    @GetMapping("/getReplies/{id}")
//    public List<ReplyDTO> getReplies(@PathVariable("id") Long id) {
//        return messageService.getReplies(id);
//    }
}
