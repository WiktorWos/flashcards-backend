package com.wiktor.wos.flashcards.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SetDto {
    private Long id;
    private String name;
    private String language;
    private String category;
    private LocalDateTime createDate;
    private boolean isPublic;
}
