package com.example.BlaBlaCar.entities.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageDTO {

    private String text;

    private Long groupId;

    private Long messageDeleteId;

    private String ownerName;
}
