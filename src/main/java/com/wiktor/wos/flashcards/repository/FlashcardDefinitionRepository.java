package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.Flashcard;
import com.wiktor.wos.flashcards.entity.FlashcardDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashcardDefinitionRepository extends JpaRepository<FlashcardDefinition, Long> {
    Optional<FlashcardDefinition> findByFrontDefinitionAndBackDefinition(String frontDefinition, String backDefinition);
    Optional<FlashcardDefinition> findById(Long id);
    Optional<FlashcardDefinition> findByFrontDefinitionAndBackDefinitionAndFlashcard(String frontDefinition, String backDefinition, Flashcard flashcard);
}
