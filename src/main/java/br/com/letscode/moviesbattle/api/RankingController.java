package br.com.letscode.moviesbattle.api;

import br.com.letscode.moviesbattle.api.model.payload.request.RankingRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @GetMapping
    public ResponseEntity<?> getRanking() {
        return ResponseEntity.ok(RankingRequest.builder().build());
    }
}
