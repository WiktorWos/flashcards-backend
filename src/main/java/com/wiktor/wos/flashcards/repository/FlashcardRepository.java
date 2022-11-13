package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
