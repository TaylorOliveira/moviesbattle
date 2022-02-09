package br.com.letscode.moviesbattle.api.controller;

import br.com.letscode.moviesbattle.domain.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping
    public ResponseEntity<?> getRanking() {

        return ResponseEntity.ok(rankingService.getRanking());
    }
}
