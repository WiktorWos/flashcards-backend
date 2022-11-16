package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    Optional<Flashcard> findByBackAndFront(String back, String front);
}
