package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.service.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/sets")
public class SetController {
    private final SetService setService;

    public SetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping
    public ResponseEntity<SetDto> createSet(@RequestBody SetDto dto) throws Exception {
        return new ResponseEntity<>(setService.createSet(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<SetDto> updateSet(@PathVariable Long id, @RequestBody SetDto dto) throws Exception {
        return new ResponseEntity<>(setService.updateSet(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteSet(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(setService.deleteSet(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SetDto>> getAllSets() throws Exception {
        return new ResponseEntity<>(setService.getAllSets(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SetDto> getSet(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(setService.getSet(id), HttpStatus.OK);
    }
}
