package br.com.letscode.moviesbattle.api.model.payload.response;

import br.com.letscode.moviesbattle.domain.model.enums.GameStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoundGameResponse {

    private Long roundId;
    private MovieResponse leftMovie;
    private MovieResponse rightMovie;
    private int round;
    private GameResponse game;

    @Data
    @Builder
    public static class MovieResponse {

        private Long id;
        private String name;
        private String debutYear;
    }

    @Data
    @Builder
    public static class GameResponse {

        private Long id;
        private int totalErrors;
        private GameStatusEnum status;
    }
}
