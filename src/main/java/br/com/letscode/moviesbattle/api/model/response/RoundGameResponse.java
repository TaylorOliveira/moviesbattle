package br.com.letscode.moviesbattle.api.model.response;

import br.com.letscode.moviesbattle.domain.model.enums.StatusGame;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoundGameResponse {

    private Long id;
    private MovieResponse leftMovie;
    private MovieResponse rightMovie;
    private int round;
    private GameResponse game;

    @Data
    @Builder
    public static class GameResponse {

        private Long id;
        private Long totalGamePoint;
        private Long totalErrors;
        private StatusGame status;
    }

    @Data
    @Builder
    public static class MovieResponse {

        private Long id;
        private String name;
    }
}
