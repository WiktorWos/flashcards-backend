package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flashcard_definitions")
@Getter
@Setter
public class FlashcardDefinition extends GenericEntity {
    private String frontDefinition;
    private String backDefinition;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Flashcard flashcard;
}
