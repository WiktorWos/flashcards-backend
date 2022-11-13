package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.SetDto;

import java.util.List;

public interface SetService {
    SetDto createSet(SetDto setDto) throws Exception;
    SetDto updateSet(SetDto setDto, Long id);
    Long deleteSet(Long id);
    List<SetDto> getAllSets();
    SetDto getSet(Long id);
}
