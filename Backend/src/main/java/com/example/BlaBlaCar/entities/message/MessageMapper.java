package com.example.BlaBlaCar.entities.message;

import com.example.BlaBlaCar.entities.reply.Reply;
import com.example.BlaBlaCar.entities.reply.ReplyDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface MessageMapper {
    Message fromMessageDTO(MessageDTO messageDTO);

    @Mapping(source = "message.id", target = "messageDeleteId")
    @Mapping(source = "message.group.id", target = "groupId")
    MessageDTO fromMessage(Message message);

    ReplyDTO fromReply(Reply reply);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(MessageDTO MessageDTO, @MappingTarget Message Message);
}
