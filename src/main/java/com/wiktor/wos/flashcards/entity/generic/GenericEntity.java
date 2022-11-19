package com.wiktor.wos.flashcards.entity.generic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
