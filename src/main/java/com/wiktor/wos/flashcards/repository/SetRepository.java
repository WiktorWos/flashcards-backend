package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
}
