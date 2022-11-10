package com.wiktor.wos.flashcards.repository;

import com.wiktor.wos.flashcards.entity.TimeLogs;
import com.wiktor.wos.flashcards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}
