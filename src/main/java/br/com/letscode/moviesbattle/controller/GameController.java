package br.com.letscode.moviesbattle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @GetMapping("/start")
    public ResponseEntity<?> initializeGame(final Authentication authentication) {

        return ResponseEntity.ok("");
    }
}