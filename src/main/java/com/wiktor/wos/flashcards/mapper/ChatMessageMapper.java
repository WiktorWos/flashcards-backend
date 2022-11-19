package com.wiktor.wos.flashcards.mapper;

import com.wiktor.wos.flashcards.dto.MessageDto;
import com.wiktor.wos.flashcards.entity.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper extends GenericMapper<ChatMessage, MessageDto> {
}
