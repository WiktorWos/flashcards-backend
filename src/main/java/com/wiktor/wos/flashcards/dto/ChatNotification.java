package com.wiktor.wos.flashcards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderName;
}
