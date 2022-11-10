package com.wiktor.wos.flashcards.dto;

import javax.validation.constraints.NotNull;

public class SetStatsDTO {
    @NotNull
    private Long setID;
    @NotNull
    private Integer doneFlashcards;
    @NotNull
    private Integer correctFlashcards;

    public Long getSetID() {
        return setID;
    }

    public void setSetID(Long setID) {
        this.setID = setID;
    }

    public Integer getDoneFlashcards() {
        return doneFlashcards;
    }

    public void setDoneFlashcards(Integer doneFlashcards) {
        this.doneFlashcards = doneFlashcards;
    }

    public Integer getCorrectFlashcards() {
        return correctFlashcards;
    }

    public void setCorrectFlashcards(Integer correctFlashcards) {
        this.correctFlashcards = correctFlashcards;
    }
}
