package com.example.BlaBlaCar.entities.message;

import com.example.BlaBlaCar.entities.reply.ReplyDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
@Setter
public class MessageDTO {

    private String text;

    private Long groupId;

    private Long messageDeleteId;

    private String ownerName;

    private Set<ReplyDTO> replies;

    private Date date;
}
