package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
public class Flashcard extends GenericEntity {
    private String front;
    private String back;
}
