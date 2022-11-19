package com.wiktor.wos.flashcards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private Long chatId;
    private Long recipientId;
    private String content;
}
