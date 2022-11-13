package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.SetDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sharedSets")
public class SharedSetController {
    @PostMapping
    public ResponseEntity<SetDto> addSharedSet() throws Exception {
        return new ResponseEntity<>(new SetDto(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteFlashcard(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @PostMapping("browser")
    public ResponseEntity<SetDto> browseSharedSets() throws Exception {
        return new ResponseEntity<>(new SetDto(), HttpStatus.OK);
    }
}