package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.SetStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetStatsRepo extends JpaRepository<SetStats, Long> {
    SetStats findBySetID(Long setID);
}
