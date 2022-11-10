package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.TimeLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLogsRepo extends JpaRepository<TimeLogs, Long> {
}
