package com.wiktor.wos.flashcards.controller;

import com.wiktor.wos.flashcards.dto.SetDto;
import com.wiktor.wos.flashcards.security.MyUserPrincipal;
import com.wiktor.wos.flashcards.service.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sharedSets")
public class SharedSetController {
    private final SetService setService;

    public SharedSetController(SetService setService) {
        this.setService = setService;
    }

    @PostMapping("{id}")
    public ResponseEntity<SetDto> addSharedSet(@PathVariable Long id, Authentication authentication) throws Exception {
        return new ResponseEntity<>(setService.addSharedSet(id, authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteSharedSet(@PathVariable Long id, Authentication authentication) throws Exception {
        return new ResponseEntity<>(setService.deleteSharedSet(id, authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("browser")
    public ResponseEntity<SetDto> browseSharedSets() throws Exception {
        return new ResponseEntity<>(new SetDto(), HttpStatus.OK);
    }
}