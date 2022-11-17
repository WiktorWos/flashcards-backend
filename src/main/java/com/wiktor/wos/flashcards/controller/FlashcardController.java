package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.FlashcardDto;
import com.wiktor.wos.flashcards.dto.PlayedFlashcardDto;
import com.wiktor.wos.flashcards.service.FlashcardService;
import com.wiktor.wos.flashcards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flashcards")
@RequiredArgsConstructor
public class FlashcardController {
    private final FlashcardService flashcardService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PlayedFlashcardDto> createFlashcard(@RequestBody PlayedFlashcardDto dto, Authentication authentication) throws Exception {
        var userId = userService.findByEmail(authentication.getName()).get(0).getUserId();
        dto.setUserId(userId);
        return new ResponseEntity<>(flashcardService.createFlashcard(dto), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PlayedFlashcardDto> updateFlashcard(@RequestBody PlayedFlashcardDto dto, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(flashcardService.updateFlashcard(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteFlashcard(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(flashcardService.deleteFlashcard(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/correct")
    public ResponseEntity<Void> markFlashcardAsRemembered(@PathVariable Long id) throws Exception {
        flashcardService.markFlashcardAsRemembered(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/wrong")
    public ResponseEntity<Void> markFlashcardAsForgotten(@PathVariable Long id) throws Exception {
        flashcardService.markFlashcardAsForgotten(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
