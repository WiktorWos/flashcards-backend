package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.UserSet;
import com.wiktor.wos.flashcards.entity.UserSetsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSetRepository extends JpaRepository<UserSet, UserSetsId> {
}
