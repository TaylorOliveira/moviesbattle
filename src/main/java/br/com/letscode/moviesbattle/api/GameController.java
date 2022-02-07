package br.com.letscode.moviesbattle.api;

import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/initialize")
    public ResponseEntity<?> initializeGame(final Authentication authentication) {
        // final LoggedInUser loggedInUser = (LoggedInUser) authentication;

        Game game = gameService.initializeGame(Long.valueOf("1"));
        return ResponseEntity.ok(game);
    }
}