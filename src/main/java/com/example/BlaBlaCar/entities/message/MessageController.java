package com.example.BlaBlaCar.entities.message;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody MessageDTO messageDTO) {
        return messageService.saveMessage(messageDTO);
    }

    @PostMapping("/delete")
    public void delete(@Valid @RequestBody MessageDTO messageDTO) {
        messageService.deleteMessage(messageDTO);
    }

    @GetMapping("/getById/{id}")
    public MessageDTO getById(@PathVariable("id") Long id) {
        return messageService.getById(id);
    }

    @GetMapping("/getAll")
    public List<MessageDTO> getAll() {
        return messageService.getAll();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable("id") Long id, @Valid @RequestBody MessageDTO messageDTO) {
        return messageService.updateMessage(messageDTO, id);
    }
}
