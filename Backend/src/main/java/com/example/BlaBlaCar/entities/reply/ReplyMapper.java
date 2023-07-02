package com.example.BlaBlaCar.entities.reply;

import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface ReplyMapper {
    Reply fromReplyDTO(ReplyDTO replyDTO);

    ReplyDTO fromReply(Reply reply);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReplyDTO replyDTO, @MappingTarget Reply reply);
}
