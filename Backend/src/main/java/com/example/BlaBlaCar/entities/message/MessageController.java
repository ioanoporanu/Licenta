package com.example.BlaBlaCar.entities.message;

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

    @DeleteMapping("/delete")
    public void delete(@Valid @RequestBody MessageDTO messageDTO) {
        messageService.deleteMessage(messageDTO);
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
}
