package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.PlayedFlashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayedFlashcardRepository extends JpaRepository<PlayedFlashcard, Long> {
}
