package com.example.BlaBlaCar.entities.message;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface MessageMapper {
    Message fromMessageDTO(MessageDTO messageDTO);

    MessageDTO fromMessage(Message message);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(MessageDTO MessageDTO, @MappingTarget Message Message);
}
