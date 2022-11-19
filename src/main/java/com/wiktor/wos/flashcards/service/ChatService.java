package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.MessageDto;

public interface ChatService {
    void sendMessage(MessageDto dto, String username);
}
