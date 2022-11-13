package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "played_flashcards")
@Getter
@Setter
public class PlayedFlashcard extends GenericEntity {
    private Integer currentLevel;
    private LocalDateTime levelChangeDate;

    @ManyToOne
    private Set set;

    @ManyToOne
    private FlashcardDefinition flashcardDefinition;
}
