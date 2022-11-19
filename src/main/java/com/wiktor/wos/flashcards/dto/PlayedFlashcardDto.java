package com.wiktor.wos.flashcards.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayedFlashcardDto {
    private Long id;
    private Integer currentLevel;
    private LocalDateTime levelChangeDate;
    private Long setId;
    private Long userId;
    private String front;
    private String back;
    private String frontDefinition;
    private String backDefinition;
}
