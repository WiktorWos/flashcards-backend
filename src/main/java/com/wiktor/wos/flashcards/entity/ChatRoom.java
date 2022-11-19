package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat_room")
public class ChatRoom extends GenericEntity {
    private LocalDateTime createDate;
    @ManyToOne
    @JoinColumn(name = "user_a_id")
    private User userA;
    @ManyToOne
    @JoinColumn(name = "user_b_id")
    private User userB;
}
