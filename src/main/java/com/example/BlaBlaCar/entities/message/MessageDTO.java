package com.example.BlaBlaCar.entities.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String text;

    private String title;

    private Long groupId;

    private Long messageDeleteId;
}
