package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import com.wiktor.wos.flashcards.enums.MessageStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat_message")
public class ChatMessage extends GenericEntity {
    private String content;
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @ManyToOne
    private ChatRoom chatRoom;
    @ManyToOne
    private User sender;
}
