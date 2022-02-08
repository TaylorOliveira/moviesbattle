package br.com.letscode.moviesbattle.api.model.response;

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
}
