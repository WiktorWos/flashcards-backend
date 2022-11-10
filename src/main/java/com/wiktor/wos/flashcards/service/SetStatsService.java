package com.wiktor.wos.flashcards.service;

import com.wiktor.wos.flashcards.dto.SetStatsDTO;
import com.wiktor.wos.flashcards.dto.SetStatsResponseDTO;
import com.wiktor.wos.flashcards.entity.SetStats;
import com.wiktor.wos.flashcards.exception.EntityNotFoundException;
import com.wiktor.wos.flashcards.repository.SetStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetStatsService {
    private SetStatsRepo setStatsRepo;

    @Autowired
    public SetStatsService(SetStatsRepo setStatsRepo) {
        this.setStatsRepo = setStatsRepo;
    }

    public SetStatsResponseDTO getStatsBySetID(Long setID) {
        SetStatsResponseDTO responseDTO = new SetStatsResponseDTO();
        SetStats stats = setStatsRepo.findBySetID(setID);
        if(stats == null) throw new EntityNotFoundException("Entity not found");

        responseDTO.setAccuracy((stats.getCorrectFlashcards() * 100) / stats.getDoneFlashcards());
        return responseDTO;
    }

    public void setSetStats(SetStatsDTO dto) {
        SetStats stats = setStatsRepo.findBySetID(dto.getSetID());
        if(stats == null) {
            stats = convertStatsFromDTO(dto);
        } else {
            stats = updateStats(dto, stats);
        }

        setStatsRepo.save(stats);
    }

    private SetStats convertStatsFromDTO(SetStatsDTO dto) {
        SetStats stats = new SetStats();
        stats.setSetID(dto.getSetID());
        stats.setCorrectFlashcards(dto.getCorrectFlashcards());
        stats.setDoneFlashcards(dto.getDoneFlashcards());
        return stats;
    }

    private SetStats updateStats(SetStatsDTO dto, SetStats stats) {
        stats.setDoneFlashcards(stats.getDoneFlashcards() + dto.getDoneFlashcards());
        stats.setCorrectFlashcards(stats.getCorrectFlashcards() + dto.getCorrectFlashcards());
        return stats;
    }
}
