package br.com.letscode.moviesbattle.api;

import br.com.letscode.moviesbattle.api.model.enums.ChoiceMovieEnum;
import br.com.letscode.moviesbattle.api.model.response.RoundGameResponse;
import br.com.letscode.moviesbattle.core.security.service.LoggedInUser;
import br.com.letscode.moviesbattle.domain.service.GameService;
import br.com.letscode.moviesbattle.domain.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private RoundService roundService;

    @PostMapping("/initialize")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> initializeGame(final Authentication authentication) {

        final LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();

        RoundGameResponse roundGame = gameService.initializeGame(loggedInUser);
        return ResponseEntity.ok(roundGame);
    }

    @PutMapping("/finalize/{gameId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> finalizeGame(@PathVariable Long gameId,
                                          final Authentication authentication) {

        final LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();

        return ResponseEntity.ok(gameService.finalizeGame(gameId));
    }

    @PutMapping("/quiz/validate/{roundId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> validateQuiz(@PathVariable Long roundId,
                                          @RequestParam @Valid ChoiceMovieEnum choice,
                                          final Authentication authentication) {

        final LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();

        roundService.processRound(loggedInUser, roundId, choice);

        return ResponseEntity.ok("");
    }

    @PutMapping("/quiz")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> nextQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.finalizeGame(id));
    }
}