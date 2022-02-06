package br.com.letscode.moviesbattle.controller;

import br.com.letscode.moviesbattle.controller.payload.request.game.RankingRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @GetMapping
    public ResponseEntity<RankingRequest> getRanking() {
        return ResponseEntity.ok(RankingRequest.builder().build());
    }
}
