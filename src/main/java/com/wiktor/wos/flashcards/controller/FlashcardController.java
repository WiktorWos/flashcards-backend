package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.FlashcardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flashcards")
public class FlashcardController {
    @PostMapping
    public ResponseEntity<FlashcardDto> createFlashcard() throws Exception {
        return new ResponseEntity<>(new FlashcardDto(), HttpStatus.CREATED);
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
