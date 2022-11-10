package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatsRepo extends JpaRepository<UserStats, Long> {
    UserStats findByUserID(Long userID);
}
