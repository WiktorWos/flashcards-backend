package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
