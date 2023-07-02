package com.example.BlaBlaCar.entities.reply;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @PostMapping("/create")
    public Long save(@Valid @RequestBody ReplyDTO replyDTO) {
        return replyService.saveReply(replyDTO);
    }

    @GetMapping("/getAll/{id}")
    public List<ReplyDTO> getAll(@PathVariable("id") Long id) {
        return replyService.getAll(id);
    }
}
