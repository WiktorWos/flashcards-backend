package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;
import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.exception.BadRequestException;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.mapper.AbstractPlayedFlashcardMapper;
import com.wiktor.wos.flashcards.repository.PlayedFlashcardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FlashcardServiceImpl implements FlashcardService {
    private final PlayedFlashcardRepository playedFlashcardRepository;
    private final AbstractPlayedFlashcardMapper playedFlashcardMapper;
    private final int MAX_LEVEL = 5;

    public FlashcardServiceImpl(PlayedFlashcardRepository playedFlashcardRepository, AbstractPlayedFlashcardMapper playedFlashcardMapper) {
        this.playedFlashcardRepository = playedFlashcardRepository;
        this.playedFlashcardMapper = playedFlashcardMapper;
    }

    @Override
    public PlayedFlashcardDto createFlashcard(PlayedFlashcardDto dto) {
        if(dto.getId() != null) throw new BadRequestException("Id to create must be null");
        var mappedEntity = playedFlashcardMapper.mapToEntity(dto);
        mappedEntity.setCurrentLevel(1);
        var savedEntity = playedFlashcardRepository.save(mappedEntity);
        return playedFlashcardMapper.mapToDto(savedEntity);
    }

    @Override
    public PlayedFlashcardDto updateFlashcard(PlayedFlashcardDto dto, Long id) {
        if(id == null) throw new BadRequestException("Id to create must not be null");

        playedFlashcardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flashcard not found"));
        dto.setId(id);
        var savedEntity = playedFlashcardRepository.save(playedFlashcardMapper.mapToEntity(dto));
        return playedFlashcardMapper.mapToDto(savedEntity);
    }

    @Override
    public Long deleteFlashcard(Long id) {
        playedFlashcardRepository.deleteById(id);
        return id;
    }

    @Override
    public void markFlashcardAsRemembered(Long id) {
        var flashcard = playedFlashcardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flashcard not found"));
        var currentLevel = flashcard.getCurrentLevel();
        if (currentLevel <= MAX_LEVEL) {
            flashcard.setCurrentLevel(++currentLevel);
            flashcard.setLevelChangeDate(LocalDateTime.now());
        }
        playedFlashcardRepository.save(flashcard);
    }

    @Override
    public void markFlashcardAsForgotten(Long id) {
        var flashcard = playedFlashcardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flashcard not found"));
        var currentLevel = flashcard.getCurrentLevel();
        if (currentLevel <= MAX_LEVEL) {
            flashcard.setCurrentLevel(1);
            flashcard.setLevelChangeDate(LocalDateTime.now());
        }
        playedFlashcardRepository.save(flashcard);
    }
}
