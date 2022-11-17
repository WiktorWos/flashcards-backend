package com.wiktor.wos.flashcards.entity;

import com.wiktor.wos.flashcards.entity.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sets")
@Getter
@Setter
public class Set extends GenericEntity {
    private String name;
    private String language;
    private boolean isPublic;
    private LocalDateTime createDate;
    private String category;

    @ManyToOne()
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_sets",
        joinColumns = {@JoinColumn(name = "set_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;
}
