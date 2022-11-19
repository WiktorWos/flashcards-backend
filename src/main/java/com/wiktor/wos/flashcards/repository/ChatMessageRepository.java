package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
