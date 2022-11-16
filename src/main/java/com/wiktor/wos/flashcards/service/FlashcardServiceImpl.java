package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;
import com.wiktor.wos.flashcards.exception.BadRequestException;
import com.wiktor.wos.flashcards.mapper.AbstractPlayedFlashcardMapper;
import com.wiktor.wos.flashcards.repository.PlayedFlashcardRepository;
import org.springframework.stereotype.Service;

@Service
public class FlashcardServiceImpl implements FlashcardService {
    private final PlayedFlashcardRepository playedFlashcardRepository;
    private final AbstractPlayedFlashcardMapper playedFlashcardMapper;

    public FlashcardServiceImpl(PlayedFlashcardRepository playedFlashcardRepository, AbstractPlayedFlashcardMapper playedFlashcardMapper) {
        this.playedFlashcardRepository = playedFlashcardRepository;
        this.playedFlashcardMapper = playedFlashcardMapper;
    }

    @Override
    public PlayedFlashcardDto createFlashcard(PlayedFlashcardDto dto) {
        if(dto.getId() != null) throw new BadRequestException("Id to create must be null");
        var mappedEntity = playedFlashcardMapper.mapToEntity(dto);
        var savedEntity = playedFlashcardRepository.save(mappedEntity);
        return playedFlashcardMapper.mapToDto(savedEntity);
    }

    @Override
    public PlayedFlashcardDto updateFlashcard(PlayedFlashcardDto dto, Long id) {
        return null;
    }

    @Override
    public Long deleteFlashcard(Long id) {
        return null;
    }

    @Override
    public Long markFlashcardAsRemembered(Long id) {
        return null;
    }

    @Override
    public Long markFlashcardAsForgotten(Long id) {
        return null;
    }
}
