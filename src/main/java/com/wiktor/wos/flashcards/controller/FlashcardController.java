package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.FlashcardDto;
import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;
import com.wiktor.wos.flashcards.service.FlashcardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flashcards")
public class FlashcardController {
    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping
    public ResponseEntity<PlayedFlashcardDto> createFlashcard(@RequestBody PlayedFlashcardDto dto) throws Exception {
        return new ResponseEntity<>(flashcardService.createFlashcard(dto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<FlashcardDto> updateFlashcard(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(new FlashcardDto(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteFlashcard(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @PatchMapping("/{id}/correct")
    public ResponseEntity<FlashcardDto> markFlashcardAsRemembered(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(new FlashcardDto(), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/wrong")
    public ResponseEntity<FlashcardDto> markFlashcardAsForgotten(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(new FlashcardDto(), HttpStatus.CREATED);
    }
}
