package br.com.letscode.moviesbattle.api;

import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.model.Game;
import br.com.letscode.moviesbattle.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/initialize")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> initializeGame(final Authentication authentication) {

        final LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();

        RoundGameResponse roundGame = gameService.initializeGame(loggedInUser);
        return ResponseEntity.ok(roundGame);
    }

    @PutMapping("/finalize/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> finalizeGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.finalizeGame(id));
    }
}