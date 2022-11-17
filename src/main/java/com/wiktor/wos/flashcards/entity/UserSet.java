package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_sets")
@Getter
@Setter
@IdClass(UserSetsId.class)
public class UserSet {
    @Id
    private Long userId;
    @Id
    private Long setId;
}
