package com.example.BlaBlaCar.entities.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class ReplyDTO {

    private String text;

    private Long messageId;

    private String ownerUsername;

    private Date date;
}
