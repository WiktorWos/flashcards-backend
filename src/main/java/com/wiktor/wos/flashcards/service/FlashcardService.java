package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.FlashcardDto;
import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;

public interface FlashcardService {
    PlayedFlashcardDto createFlashcard(PlayedFlashcardDto dto);
    PlayedFlashcardDto updateFlashcard(PlayedFlashcardDto dto, Long id);
    Long deleteFlashcard(Long id);
    Long markFlashcardAsRemembered(Long id);
    Long markFlashcardAsForgotten(Long id);
}
