package com.wiktor.wos.flashcards.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserSetsId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "set_id")
    private Long setId;
}
