package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "played_flashcards")
@Getter
@Setter
public class PlayedFlashcard extends GenericEntity {
    private Integer currentLevel;
    private LocalDateTime levelChangeDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserSet set;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private FlashcardDefinition flashcardDefinition;
}
