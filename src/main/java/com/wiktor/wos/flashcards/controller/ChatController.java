package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.MessageDto;
import com.wiktor.wos.flashcards.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDto dto, Authentication authentication) {
       chatService.sendMessage(dto, authentication.getName());
    }
}
