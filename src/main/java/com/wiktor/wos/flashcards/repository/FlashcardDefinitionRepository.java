package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.FlashcardDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardDefinitionRepository extends JpaRepository<FlashcardDefinition, Long> {
}
