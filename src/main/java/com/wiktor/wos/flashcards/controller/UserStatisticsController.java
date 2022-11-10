package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.UserStatsDTO;
import com.wiktor.wos.flashcards.dto.UserStatsResponseDTO;
import com.wiktor.wos.flashcards.service.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/stats/user")
public class UserStatisticsController {
    private UserStatsService userStatsService;

    @Autowired
    public UserStatisticsController(UserStatsService userStatsService) {
        this.userStatsService = userStatsService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserStatsResponseDTO> getUserStatsByID(@PathVariable Long userID) {
        UserStatsResponseDTO stats = userStatsService.getStatsByUserID(userID);
        return ResponseEntity.ok(stats);
    }

    @PostMapping
    public ResponseEntity<Void> updateStats(@RequestBody @Validated UserStatsDTO dto) throws Exception {
        userStatsService.setUserStats(dto);
        return ResponseEntity.created(new URI("")).build();
    }
}
