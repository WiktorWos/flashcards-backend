package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.SetStatsDTO;
import com.wiktor.wos.flashcards.dto.SetStatsResponseDTO;
import com.wiktor.wos.flashcards.service.SetStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/stats/set")
public class SetStatisticsController {
    private SetStatsService setstatsService;

    @Autowired
    public SetStatisticsController(SetStatsService setstatsService) {
        this.setstatsService = setstatsService;
    }

    @GetMapping("/{setID}")
    public ResponseEntity<SetStatsResponseDTO> getSetStatsByID(@PathVariable Long setID) {
        SetStatsResponseDTO stats = setstatsService.getStatsBySetID(setID);
        return ResponseEntity.ok(stats);
    }

    @PostMapping
    public ResponseEntity<Void> updateStats(@RequestBody @Validated SetStatsDTO dto) throws Exception {
        setstatsService.setSetStats(dto);
        return ResponseEntity.created(new URI("")).build();
    }
}
