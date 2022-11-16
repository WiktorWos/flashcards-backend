package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_sets")
@Getter
@Setter
public class UserSet extends GenericEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Set set;
}
